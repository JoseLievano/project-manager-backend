package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClientServiceImplements extends DefaultServiceImplements<ClientDTO, ClientMiniDTO, ClientListDTO, ClientForm, ClientEntity, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MainHQRepository mainHQRepository;

    public ClientServiceImplements(ClientRepository repository, ClientMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ClientMiniDTO insert(ClientForm clientForm) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (clientForm == null || clientForm.getPassword() == null || clientForm.getEmail() == null) {
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new client");
        }

        //Check if the client already exist in our DB
        Set<ClientEntity> clientExistenceCheck = clientRepository.findByUsername(clientForm.getUsername());

        clientExistenceCheck.addAll(clientRepository.findByEmail(clientForm.getEmail()));

        if (clientExistenceCheck.size() > 0) {
            throw new ElementAlreadyExist();
        }

        //Encode the password
        clientForm.setPassword(passwordEncoder.encode(clientForm.getPassword()));
        ClientEntity toInsert = mapper.toEntity(clientForm);

        //Set the role
        Set<String> roles = Set.of("ROLE_CLIENT");
        toInsert.setRoles(roles);

        //Insert the MainHQ in the client
        MainHQEntity mainHQEntity = mainHQRepository.findAll().get(0);

        if (mainHQEntity == null) {
            throw new ElementNotFoundException("The MainHQ is not found in our DB");
        }else {
            toInsert.setMainHQEntity(mainHQEntity);
        }

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);
    }

    @Override
    public ClientDTO delete(Long id) throws ElementNotFoundException {

        ClientEntity toDelete = repository.findById(id).orElseThrow(() -> new ElementNotFoundException("The client with id: " + id + " is not found"));

        ClientDTO clientDTO = mapper.toDTO(toDelete);

        clientDTO.setRoles(toDelete.getRoles());

        //Bug: Is necessary to sout the roles of the client to return the correct DTO
        //If not, then the client will be deleted but the roles will be null
        clientDTO.getRoles().forEach(role -> System.out.println("ClientServiceImplements delete() -> client roles: " + role));

        repository.delete(toDelete);


        return clientDTO;

    }

    @Override
    public ClientDTO update(Long ID, ClientForm form) throws ElementNotFoundException {

        if (form.getUsername() == null){
            throw new ElementNotFoundException("You must specify the username");
        }

        ClientEntity toUpdate = repository.findById(ID).orElse(clientRepository.findByUsername(form.getUsername()).stream().findFirst().orElseThrow(() -> new ElementNotFoundException("The client is not found")));

        if (form.getFirstName() != null && !form.getFirstName().equals(toUpdate.getFirstName())){
            toUpdate.setFirstName(form.getFirstName());
        }

        if (form.getLastName() != null && !form.getLastName().equals(toUpdate.getLastName())){
            toUpdate.setLastName(form.getLastName());
        }

        if (form.getEmail() != null && !form.getEmail().equals(toUpdate.getEmail())){
            toUpdate.setEmail(form.getEmail());
        }

        if (form.getPassword() != null && !form.getPassword().equals(toUpdate.getPassword())){
            toUpdate.setPassword(passwordEncoder.encode(form.getPassword()));
        }

        repository.save(toUpdate);

        return mapper.toDTO(toUpdate);

    }
}


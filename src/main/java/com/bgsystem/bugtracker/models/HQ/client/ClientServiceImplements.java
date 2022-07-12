package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClientServiceImplements extends DefaultServiceImplements<ClientDTO, ClientMiniDTO, ClientForm, ClientEntity, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    public ClientServiceImplements(ClientRepository repository, ClientMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ClientMiniDTO insert(ClientForm clientForm) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

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

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }
}


package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidDeleteOperation;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class bsPriorityServiceImplements extends DefaultServiceImplements <bsPriorityDTO, bsPriorityMiniDTO, bsPriorityListDTO, bsPriorityForm, bsPriorityEntity, Long> {

    private final BusinessRepository businessRepository;

    private final bsPriorityRepository priorityRepository;

    private final bsPriorityPredicate priorityPredicate;
    @Autowired
    public bsPriorityServiceImplements(
        bsPriorityRepository repository,
        bsPriorityMapper mapper,
        BusinessRepository businessRepository,
        bsPriorityRepository priorityRepository,
        bsPriorityPredicate bsPriorityPredicate
    ) {
        super( repository, mapper, bsPriorityPredicate );
        this.businessRepository = businessRepository;
        this.priorityRepository = priorityRepository;
        this.priorityPredicate = bsPriorityPredicate;
    }

    @Override
    public bsPriorityMiniDTO insert(bsPriorityForm bsPriorityForm) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if ( bsPriorityForm == null || bsPriorityForm.getBusiness() == null || bsPriorityForm.getName() == null ) {
            throw new InvalidInsertDeails("Invalid insert details, can´t create new priority");
        }

        //Check if a priority with the same name already exists
        if ( priorityRepository.findByName(bsPriorityForm.getName()).size() > 0 ) {
            throw new ElementAlreadyExist("Priority with that name already exists");
        }

        bsPriorityEntity toInsert = mapper.toEntity(bsPriorityForm);

        //Get the business
        BusinessEntity business = businessRepository.findById(bsPriorityForm.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));

        //Add the business to the priority
        toInsert.setBusiness(business);

        //Add the new priority to the business
        business.getBsPriorities().add(toInsert);

        //Save the business
        businessRepository.save(business);

        //Get how many priorities business already has
        long amountOfPriorities = business.getBsPriorities().size();
        toInsert.setPriorityOrder((int) amountOfPriorities);

        //Save the priority
        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }

    @Override
    public bsPriorityDTO update(Long id, bsPriorityForm form) throws ElementNotFoundException, InvalidInsertDeails {

        if (form == null)
            throw new InvalidInsertDeails();

        bsPriorityEntity toEdit = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        if (form.getName() != null)
            toEdit.setName(form.getName());


        if (form.getPriorityOrder() != null)
            toEdit.setPriorityOrder(form.getPriorityOrder());

        repository.save(toEdit);

        return mapper.toDTO(toEdit);
    }

    public Set<bsPriorityListDTO> updateOrder (Set<bsPriorityForm> formPriorities) throws ElementNotFoundException{
        Set<bsPriorityEntity> toEditPriorities = new HashSet<>();

        for (bsPriorityForm formPriority : formPriorities) {
            bsPriorityEntity toEdit = repository.findById(formPriority.getId()).orElseThrow(ElementNotFoundException::new);
            toEdit.setPriorityOrder(formPriority.getPriorityOrder());
            toEditPriorities.add(toEdit);
        }

        repository.saveAll(toEditPriorities);

        return toEditPriorities.stream()
                .map(mapper::toListDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public bsPriorityDTO delete(Long id) throws ElementNotFoundException, InvalidDeleteOperation {

        if (id == null)
            throw new InvalidDeleteOperation("Invalid priority ID");

        bsPriorityEntity toDelete = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        Integer toDelPriorityOrder = toDelete.getPriorityOrder();

        ArrayList<bsPriorityEntity> prioritiesToModify = priorityRepository.findByPriorityOrderGreaterThan(toDelPriorityOrder);

        for (bsPriorityEntity priority : prioritiesToModify){
            priority.setPriorityOrder(priority.getPriorityOrder() - 1);
        }

        repository.saveAll(prioritiesToModify);

        repository.delete(toDelete);

        return mapper.toDTO(toDelete);

    }

    @Override
    protected bsPriorityEntity updateListFields(bsPriorityEntity bsPriorityEntity) {
        bsPriorityEntity.setTaskCount(bsPriorityEntity.getTasks() == null ? 0 : (long) bsPriorityEntity.getTasks().size());
        return bsPriorityEntity;
    }
}

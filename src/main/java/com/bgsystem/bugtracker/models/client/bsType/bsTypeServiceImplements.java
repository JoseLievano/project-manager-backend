package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidDeleteOperation;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class bsTypeServiceImplements extends DefaultServiceImplements <bsTypeDTO, bsTypeMiniDTO, bsTypeListDTO, bsTypeForm, bsTypeEntity, Long> {

    private final bsTypeRepository bsTypeRepository;

    private final BusinessRepository businessRepository;

    private final bsTaskCategoryRepository bsTaskCategoryRepository;
    @Autowired
    public bsTypeServiceImplements(
                                bsTypeRepository repository,
                                bsTypeMapper mapper,
                                bsTypeRepository bsTypeRepository,
                                BusinessRepository businessRepository,
                                bsTaskCategoryRepository bsTaskCategoryRepository,
                                bsTypePredicate bsTypePredicate
    ) {
        super(repository, mapper, bsTypePredicate);
        this.bsTypeRepository = bsTypeRepository;
        this.businessRepository = businessRepository;
        this.bsTaskCategoryRepository = bsTaskCategoryRepository;
    }

    @Override
    public bsTypeMiniDTO insert(bsTypeForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if( form == null || form.getBusiness() == null || form.getName() == null || form.getTaskCategories() == null || form.getTaskCategories().size() == 0){
            throw new InvalidInsertDeails("Invalid insert details, can´t create new type");
        }

        //Check if a type with the same name already exists
        if(bsTypeRepository.findByName(form.getName()).size() > 0){
            throw new ElementAlreadyExist("Type already exists with same name");
        }

        bsTypeEntity toInsert = mapper.toEntity(form);

        //Set Task Categories

        Set<bsTaskCategoryEntity> taskCategories = new HashSet<>();
        toInsert.setTaskCategories(taskCategories);
        for (Long taskCategoryID : form.getTaskCategories()){
            //Check if the task exist
            bsTaskCategoryEntity taskCategory = bsTaskCategoryRepository.findById(taskCategoryID).orElseThrow(ElementNotFoundException::new);
            toInsert.getTaskCategories().add(taskCategory);
            taskCategory.getTypes().add(toInsert);
            taskCategories.add(taskCategory);
        }

        //Set Business
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));

        toInsert.setBusiness(business);

        business.getBsTypes().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        bsTaskCategoryRepository.saveAll(taskCategories);

        return mapper.toSmallDTO(toInsert);

    }

    @Override
    public bsTypeDTO delete(Long id) throws ElementNotFoundException, InvalidDeleteOperation {

        bsTypeEntity toDelete = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        if (toDelete.getTasks().size() > 0)
            throw new InvalidDeleteOperation("Can't delete a type containing tasks");

        Set<bsTaskCategoryEntity> taskCategories = toDelete.getTaskCategories();

        for (bsTaskCategoryEntity taskCategory : taskCategories){
            System.out.println(taskCategory.getName());
            taskCategory.getTypes().remove(toDelete);
            bsTaskCategoryRepository.save(taskCategory);
        }

        repository.delete(toDelete);

        return mapper.toDTO(toDelete);

    }

    @Override
    protected bsTypeEntity updateListFields(bsTypeEntity bsTypeEntity) {

        bsTypeEntity.setTaskCount(bsTypeEntity.getTasks() == null ? 0 : (long) bsTypeEntity.getTasks().size());
        bsTypeEntity.setTaskCategoriesCount(bsTypeEntity.getTaskCategories() == null ? 0 : (long) bsTypeEntity.getTaskCategories().size());

        return bsTypeEntity;

    }
}

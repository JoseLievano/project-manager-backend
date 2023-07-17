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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

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
    public bsTypeDTO update(Long id, bsTypeForm form) throws ElementNotFoundException, InvalidInsertDeails {

        if (form == null || form.getTaskCategories().size() == 0)
            throw new InvalidInsertDeails();

        bsTypeEntity toEdit = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        if (form.getName() != null && !form.getName().equals(toEdit.getName())){
            toEdit.setName(form.getName());
        }

        Set<Long> originalTaskCats = toEdit.getTaskCategories().stream()
                .map(bsTaskCategoryEntity::getId)
                .collect(Collectors.toSet());

        //Compare between originalTaskCats and new task cats from form.
        Iterator<Long> oriIT = originalTaskCats.iterator();
        Iterator<Long> newIT = form.getTaskCategories().iterator();

        while (oriIT.hasNext()){
            Long actualOriValue = oriIT.next();
            while (newIT.hasNext()){
                Long newITValue = newIT.next();
                if (actualOriValue.equals(newITValue)){
                    newIT.remove();
                    oriIT.remove();
                }
            }
        }

        //All task categories in originalTaskCats should be unlinked from this type
        for (Long taskCatID : originalTaskCats){
            bsTaskCategoryEntity taskCatToUnLink = bsTaskCategoryRepository.findById(taskCatID).orElseThrow(() -> new ElementNotFoundException("Can't find task category"));
            taskCatToUnLink.getTypes().remove(toEdit);
            bsTaskCategoryRepository.save(taskCatToUnLink);
        }

        //All task in form.getTaskCategories should be linked to this type
        for (Long newTaskCatID : form.getTaskCategories()){
            bsTaskCategoryEntity taskToLink = bsTaskCategoryRepository.findById(newTaskCatID).orElseThrow(() -> new ElementNotFoundException("Can't find task category to add"));
            taskToLink.getTypes().add(toEdit);
            bsTaskCategoryRepository.save(taskToLink);
            toEdit.getTaskCategories().add(taskToLink);
        }

        repository.save(toEdit);

        return mapper.toDTO(toEdit);
    }

    @Override
    public bsTypeDTO delete(Long id) throws ElementNotFoundException, InvalidDeleteOperation {

        bsTypeEntity toDelete = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        if (toDelete.getTasks().size() > 0)
            throw new InvalidDeleteOperation("Can't delete a type containing tasks");

        Set<bsTaskCategoryEntity> taskCategories = toDelete.getTaskCategories();

        for (bsTaskCategoryEntity taskCategory : taskCategories){
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

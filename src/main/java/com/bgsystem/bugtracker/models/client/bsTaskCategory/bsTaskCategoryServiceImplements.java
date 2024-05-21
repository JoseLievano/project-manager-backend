package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidDeleteOperation;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeEntity;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class bsTaskCategoryServiceImplements extends DefaultServiceImplements <bsTaskCategoryDTO, bsTaskCategoryMiniDTO, bsTaskCategoryListDTO, bsTaskCategoryForm, bsTaskCategoryEntity, Long> {

    private final bsTaskCategoryRepository bsTaskCategoryRepository;

    private final BusinessRepository businessRepository;

    private final bsTypeRepository bsTypeRepository;
    @Autowired
    public bsTaskCategoryServiceImplements(
        bsTaskCategoryRepository repository,
        bsTaskCategoryMapper mapper,
        bsTaskCategoryRepository bsTaskCategoryRepository,
        BusinessRepository businessRepository,
        bsTaskCategoryPredicate bsTaskCategoryPredicate,
        bsTypeRepository bsTypeRepository
    ) {
        super(repository, mapper, bsTaskCategoryPredicate);
        this.bsTaskCategoryRepository = bsTaskCategoryRepository;
        this.businessRepository = businessRepository;
        this.bsTypeRepository = bsTypeRepository;
    }

    @Override
    public bsTaskCategoryMiniDTO insert(bsTaskCategoryForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        //Validate form
        if(form == null || form.getBusiness() == null || form.getName() == null || form.getName().isEmpty()){
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Check if category with same name and business already exist
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));

        if (!bsTaskCategoryRepository.findByNameAndBusiness(form.getName(), business).isEmpty()){
            throw new ElementAlreadyExist("Category with same name and business already exist");
        }

        //Create the task category entity
        bsTaskCategoryEntity toInsert = mapper.toEntity(form);

        //Set the Business in the Task Category
        toInsert.setBusiness(business);

        //Set the task category in the business
        business.getBsTaskCategories().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }

    @Override
    public bsTaskCategoryDTO update(Long id, bsTaskCategoryForm form) throws ElementNotFoundException, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getName().isEmpty()){
            throw new InvalidInsertDeails("Invalid update details");
        }

        bsTaskCategoryEntity toEdit = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        toEdit.setName(form.getName());

        repository.save(toEdit);

        return mapper.toDTO(toEdit);

    }

    @Override
    public bsTaskCategoryDTO delete(Long ID) throws ElementNotFoundException, InvalidDeleteOperation {

        bsTaskCategoryEntity toDelete = repository.findById(ID).orElseThrow(ElementNotFoundException::new);

        //Check if bsTaskCategory has Tasks assigned
        if (!toDelete.getTasks().isEmpty())
            throw new InvalidDeleteOperation("The task category has " + toDelete.getTasks().size() + " tasks assigned, can't delete a task category with task assigned");

        //Check each type this bsTaskCategory has assigned
        Set<bsTypeEntity> types = toDelete.getTypes();
        if (!types.isEmpty()){
            for (bsTypeEntity type : types){
                if (type.getTaskCategories().size() < 2){
                    throw new InvalidDeleteOperation("Type: " + type.getName() + " has only this task category assigned." + " You should add one more task category to type " + type.getName() + " before deleting " + toDelete.getName() + " task category");
                }else {
                    toDelete.getTypes().remove(type);
                    type.getTaskCategories().remove(toDelete);
                }
            }
        }

        //Remove from business
        BusinessEntity business = toDelete.getBusiness();
        business.getBsTaskCategories().remove(toDelete);

        repository.delete(toDelete);

        businessRepository.save(business);

        if (!types.isEmpty()){
            bsTypeRepository.saveAll(types);
        }

        return mapper.toDTO(toDelete);

    }

    public Boolean taskCatIsEmpty(){
        Set<bsTaskCategoryEntity> taskCategoryEntities = new HashSet<>(repository.findAll());
        return taskCategoryEntities.isEmpty();
    }

    @Override
    protected bsTaskCategoryEntity updateListFields(bsTaskCategoryEntity bsTaskCategoryEntity) {
        bsTaskCategoryEntity.setTaskCount(bsTaskCategoryEntity.getTasks() == null ? 0 : (long) bsTaskCategoryEntity.getTasks().size());
        bsTaskCategoryEntity.setTypesCount(bsTaskCategoryEntity.getTypes() == null ? 0 : (long) bsTaskCategoryEntity.getTypes().size());
        return bsTaskCategoryEntity;
    }
}

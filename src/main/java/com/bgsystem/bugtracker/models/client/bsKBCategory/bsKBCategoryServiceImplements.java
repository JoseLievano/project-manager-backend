package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class bsKBCategoryServiceImplements extends DefaultServiceImplements <bsKBCategoryDTO, bsKBCategoryMiniDTO, bsKBCategoryListDTO, bsKBCategoryForm, bsKBCategoryEntity, Long> {

    private final bsKBCategoryRepository bsKBCategoryRepository;

    private final BusinessRepository businessRepository;

    private final bsKBCategoryPredicate bsKBCategoryPredicate;
    @Autowired
    public bsKBCategoryServiceImplements(
                                        bsKBCategoryRepository repository,
                                        bsKBCategoryMapper mapper,
                                        bsKBCategoryRepository bsKBCategoryRepository,
                                        BusinessRepository businessRepository,
                                        bsKBCategoryPredicate bsKBCategoryPredicate
    ) {
        super(repository, mapper, bsKBCategoryPredicate);
        this.bsKBCategoryRepository = bsKBCategoryRepository;
        this.businessRepository = businessRepository;
        this.bsKBCategoryPredicate = bsKBCategoryPredicate;
    }

    @Override
    public bsKBCategoryMiniDTO insert(bsKBCategoryForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getBusiness() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Check if there is a KB Category with the same name for the same business
        if (bsKBCategoryRepository.findByNameAndBusinessId(form.getName(), form.getBusiness()).size() > 0) {
            throw new ElementAlreadyExist("KB Category with the same name for the same business already exist");
        }

        bsKBCategoryEntity toInsert = mapper.toEntity(form);

        //Set parent KB Category
        if (form.getParentCategory() != null){
            bsKBCategoryEntity parentCategory = repository.findById(form.getParentCategory()).orElseThrow(() -> new ElementNotFoundException("KB Category not found"));
            toInsert.setParentKB(parentCategory);
            boolean isAParentCategory = parentCategory.getIsAParentKBCategory() != null && parentCategory.getIsAParentKBCategory();
            if (!isAParentCategory){
                parentCategory.setIsAParentKBCategory(true);
            }

            //Set the business based in the parent category business
            BusinessEntity business = parentCategory.getBusiness();
            business.getBsKBCategories().add(toInsert);
            toInsert.setBusiness(business);

            toInsert.setLevel(parentCategory.getLevel() + 1);

            businessRepository.save(business);
            repository.save(parentCategory);

        }else {
            toInsert.setLevel(0L);
            BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));
            toInsert.setBusiness(business);
            business.getBsKBCategories().add(toInsert);
            businessRepository.save(business);
        }

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }

    @Override
    public bsKBCategoryDTO update(Long id, bsKBCategoryForm form) throws ElementNotFoundException {

        bsKBCategoryEntity toUpdate = repository.findById(id).orElseThrow(() -> new ElementNotFoundException("KB Category not found"));

        if (form.getName() != null){
            toUpdate.setName(form.getName());
        }

        if (form.getDescription() != null) {
            toUpdate.setDescription(form.getDescription());
        }


        if (form.getParentCategory() != null){
            bsKBCategoryEntity parentCategory = repository.findById(form.getParentCategory()).orElseThrow(() -> new ElementNotFoundException("KB Category not found"));
            if (Objects.equals(parentCategory.getId(), toUpdate.getId())){
                throw new ElementNotFoundException("KB Category cannot be the parent of itself");
            }
            toUpdate.setParentKB(parentCategory);
            parentCategory.getSubKBCategories().add(toUpdate);
            parentCategory.setIsAParentKBCategory(true);
            repository.save(parentCategory);
            toUpdate.setLevel(parentCategory.getLevel() + 1);
        }

        //Check Category level
        if (toUpdate.getParentKB()  == null){
            toUpdate.setLevel(0L);
        } else if (toUpdate.getLevel() == null) {
            toUpdate.setLevel(toUpdate.getParentKB().getLevel() + 1);
        }

        repository.save(toUpdate);

        return mapper.toDTO(toUpdate);

    }

    @Override
    protected bsKBCategoryEntity updateListFields(bsKBCategoryEntity bsKBCategoryEntity) {

        bsKBCategoryEntity.setBsKBCount(bsKBCategoryEntity.getBsKBEntities() == null ? 0 : (long) bsKBCategoryEntity.getBsKBEntities().size());
        bsKBCategoryEntity.setSubKBCategoriesCount(bsKBCategoryEntity.getSubKBCategories() == null ? 0 : (long) bsKBCategoryEntity.getSubKBCategories().size());

        return bsKBCategoryEntity;
    }
}

package com.bgsystem.bugtracker.models.client.bsDocsCategory;

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
public class bsDocsCategoryServiceImplements extends DefaultServiceImplements <bsDocsCategoryDTO, bsDocsCategoryMiniDTO, bsDocsCategoryListDTO, bsDocsCategoryForm, bsDocsCategoryEntity, Long> {

    private final bsDocsCategoryRepository bsDocsCategoryRepository;

    private final BusinessRepository businessRepository;

    private final bsDocsCategoryPredicate bsDocsCategoryPredicate;

    @Autowired
    public bsDocsCategoryServiceImplements(
                                        bsDocsCategoryRepository repository,
                                        bsDocsCategoryMapper mapper,
                                        bsDocsCategoryRepository bsDocsCategoryRepository,
                                        BusinessRepository businessRepository,
                                        bsDocsCategoryPredicate bsDocsCategoryPredicate
    ) {
        super(repository, mapper, bsDocsCategoryPredicate);
        this.bsDocsCategoryRepository = bsDocsCategoryRepository;
        this.businessRepository = businessRepository;
        this.bsDocsCategoryPredicate = bsDocsCategoryPredicate;
    }

    @Override
    public bsDocsCategoryMiniDTO insert(bsDocsCategoryForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getBusiness() == null || form.getName() == null || form.getDescription() == null)
            throw new InvalidInsertDeails("Invalid insert details");

        //Check if the category already exists
        if (bsDocsCategoryRepository.findByName(form.getName()).size() > 0)
            throw new ElementAlreadyExist("Category already exists");

        bsDocsCategoryEntity toInsert = mapper.toEntity(form);

        //Set parent category
        if (form.getParentCategory() != null){
            bsDocsCategoryEntity parentCategory = repository.findById(form.getParentCategory()).orElseThrow(() -> new ElementNotFoundException("Parent category not found"));
            toInsert.setParentCategory(parentCategory);
            parentCategory.getSubCategories().add(toInsert);
            Boolean isAParentCategory = parentCategory.getIsAParentCategory() != null && parentCategory.getIsAParentCategory();
            if (!isAParentCategory){
                parentCategory.setIsAParentCategory(true);
            }

            //Set the business based in the parent business
            BusinessEntity parentBusiness = parentCategory.getBusiness();
            parentBusiness.getBsDocsCategories().add(toInsert);
            toInsert.setBusiness(parentBusiness);

            toInsert.setLevel(parentCategory.getLevel() + 1);

            businessRepository.save(parentBusiness);
            repository.save(parentCategory);

        }else {

            toInsert.setLevel(0L);

            //Set the business category
            BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));
            toInsert.setBusiness(business);
            business.getBsDocsCategories().add(toInsert);
            businessRepository.save(business);
        }

        toInsert.setIsAParentCategory(false);

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);
    }

    @Override
    public bsDocsCategoryDTO update(Long id, bsDocsCategoryForm form) throws ElementNotFoundException {

        bsDocsCategoryEntity toUpdate = repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Category not found"));

        if (form.getName() != null){
            toUpdate.setName(form.getName());
        }

        if (form.getDescription() != null){
            toUpdate.setDescription(form.getDescription());
        }

        if (form.getParentCategory() != null){
            bsDocsCategoryEntity parentCategory = repository.findById(form.getParentCategory()).orElseThrow(() -> new ElementNotFoundException("Parent category not found"));
            if (Objects.equals(parentCategory.getId(), toUpdate.getId())) {
                throw new ElementNotFoundException("Parent category cannot be the same as the current category");
            }
            toUpdate.setParentCategory(parentCategory);
            parentCategory.getSubCategories().add(toUpdate);
            parentCategory.setIsAParentCategory(true);
            repository.save(parentCategory);

            toUpdate.setLevel(parentCategory.getLevel() + 1);
        }

        //Check category level
        if (toUpdate.getParentCategory() == null){
            toUpdate.setLevel(0L);
        }else if (toUpdate.getLevel() == null){
            toUpdate.setLevel(toUpdate.getParentCategory().getLevel() + 1);
        }

        repository.save(toUpdate);

        return mapper.toDTO(toUpdate);

    }

    @Override
    protected bsDocsCategoryEntity updateListFields(bsDocsCategoryEntity bsDocsCategoryEntity) {

        bsDocsCategoryEntity.setBsDocsCount(bsDocsCategoryEntity.getBsDocs() == null ? 0 : (long) bsDocsCategoryEntity.getBsDocs().size() );
        bsDocsCategoryEntity.setSubCategoriesCount(bsDocsCategoryEntity.getBsDocsCount() == null ? 0 : (long) bsDocsCategoryEntity.getSubCategories().size());

        return bsDocsCategoryEntity;

    }
}

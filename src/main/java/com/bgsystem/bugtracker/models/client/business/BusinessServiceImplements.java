package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.models.HQ.plan.PlanRepository;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BusinessServiceImplements extends DefaultServiceImplements<BusinessDTO, BusinessMiniDTO, BusinessListDTO, BusinessForm, BusinessEntity, Long> {

    private final BusinessRepository repository;

    private final ClientRepository clientRepository;

    private final PlanRepository planRepository;

    private final bsGeneralSettingsRepository bsGeneralSettingsRepository;

    @Autowired
    public BusinessServiceImplements(
                                 BusinessRepository repository,
                                 BusinessMapper mapper,
                                 ClientRepository clientRepository,
                                 PlanRepository planRepository,
                                 bsGeneralSettingsRepository bsGeneralSettingsRepository,
                                 BusinessPredicate businessPredicate

    ){
        super(repository, mapper, businessPredicate);
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.planRepository = planRepository;
        this.bsGeneralSettingsRepository = bsGeneralSettingsRepository;
    }

    @Override
    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    public BusinessMiniDTO insert(BusinessForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getTaxID() == null || form.getClient() == null || form.getPlan() == null){
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new business");
        }

        //Check if the business already exist in our DB
        Set<BusinessEntity> businessExistenceCheck = repository.findByName(form.getName());
        if (!businessExistenceCheck.isEmpty()) {
            throw new ElementAlreadyExist("The business already exist in our DB");
        }

        BusinessEntity toInsert = mapper.toEntity(form);

        //Insert the Business client.
        toInsert.setClient(clientRepository.findById(form.getClient()).orElseThrow(ElementAlreadyExist::new));

        //Insert the Business plan.
        toInsert.setPlan(planRepository.findById(form.getPlan()).orElseThrow(ElementNotFoundException::new));

        //Create an insert a bsGeneralSettingsEntity for this business.
        bsGeneralSettingsEntity GeneralSettingsEntity = bsGeneralSettingsEntity.builder()
                .address("Demo Address")
                .email("info@demo-website.com")
                .website("www.demo-website.com")
                .business(toInsert)
                .build();

        toInsert.setBsGeneralSettings(GeneralSettingsEntity);

        repository.save(updateListFields(toInsert));

        bsGeneralSettingsRepository.save(GeneralSettingsEntity);

        return mapper.toSmallDTO(toInsert);
    }

    @Override
    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    public BusinessDTO delete(Long aLong) throws ElementNotFoundException {
        return super.delete(aLong);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN' , 'CLIENT')")
    public Collection<BusinessDTO> getAll() {
        return super.getAll();
    }

    @Override
    public BusinessDTO update(Long aLong, BusinessForm uform) throws ElementNotFoundException {
        return super.update(aLong, uform);
    }


    @Override
    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    public Page<BusinessListDTO> getPageableListView(PageableRequest pageRequest) throws BadOperator {
        return super.getPageableListView(pageRequest);
    }

    @Override
    protected BusinessEntity updateListFields(BusinessEntity business){

        business.setInvoiceCount(business.getInvoices() == null ? 0 : (long) business.getInvoices().size());
        business.setBsClientCount(business.getBsClients() == null ? 0 : (long) business.getBsClients().size());
        business.setBsManagerCount(business.getBsManagers() == null ? 0 : (long) business.getBsManagers().size());
        business.setBsEmployeeCount(business.getBsEmployees() == null ? 0 : (long) business.getBsEmployees().size());
        business.setBsStatusCount(business.getBsStatuses() == null ? 0 : (long) business.getBsStatuses().size());
        business.setBsPriorityCount(business.getBsPriorities() == null ? 0 : (long) business.getBsPriorities().size());
        business.setBsPriorityCount(business.getBsPriorities() == null ? 0 : (long) business.getBsPriorities().size());
        business.setBsTypeCount(business.getBsTypes() == null ? 0 : (long) business.getBsTypes().size());
        business.setBsDocsCategoryCount(business.getBsDocsCategories() == null ? 0 : (long) business.getBsDocsCategories().size());
        business.setBsDocCount(business.getBsDocs() == null ? 0 : (long) business.getBsDocs().size());
        business.setBsKBCategoryCount(business.getBsKBCategories() == null ? 0 : (long) business.getBsKBCategories().size());
        business.setBsKBCount(business.getBsKBs() == null ? 0 : (long) business.getBsKBs().size());
        business.setBsProjectCount(business.getBsProjects() == null ? 0 : (long) business.getBsProjects().size());
        business.setBsTaskCategoryCount(business.getBsTaskCategories() == null ? 0 : (long) business.getBsTaskCategories().size());
        business.setBsPrTaskCount(business.getBsPrTasks() == null ? 0 : (long) business.getBsPrTasks().size());
        business.setBsInvoiceCount(business.getBsInvoices() == null ? 0 : (long) business.getBsInvoices().size());

        return business;
    }

}

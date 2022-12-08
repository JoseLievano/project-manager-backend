package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanRepository;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsRepository;
import com.bgsystem.bugtracker.shared.service.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BusinessServiceImplements extends DefaultServiceImplements<BusinessDTO, BusinessMiniDTO, BusinessListDTO, BusinessForm, BusinessEntity, Long> {

    private final BusinessRepository repository;

    private final ClientRepository clientRepository;

    private final PlanRepository planRepository;

    private final bsGeneralSettingsRepository bsGeneralSettingsRepository;

    private final BusinessMapper businessMapper;

    private final EntityFactory entityFactory;

    @Autowired
    public BusinessServiceImplements(BusinessRepository repository, BusinessMapper mapper,
                                     ClientRepository clientRepository,
                                     PlanRepository planRepository,
                                     bsGeneralSettingsRepository bsGeneralSettingsRepository,
                                     BusinessMapper businessMapper,
                                        EntityFactory entityFactory
    ){
        super(repository, mapper);
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.planRepository = planRepository;
        this.bsGeneralSettingsRepository = bsGeneralSettingsRepository;
        this.businessMapper = businessMapper;
        this.entityFactory = entityFactory;
    }
    @Override
    public BusinessMiniDTO insert(BusinessForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getTaxID() == null || form.getClient() == null || form.getPlan() == null){
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new business");
        }

        //Check if the business already exist in our DB
        Set<BusinessEntity> businessExistenceCheck = repository.findByName(form.getName());
        if (businessExistenceCheck.size() > 0) {
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
    public Collection<BusinessListDTO> getAllForList(Optional<FilterRequest> listRequestRecord) throws ElementNotFoundException {

        List<BusinessListDTO> businessListDTOs = new ArrayList<>();

        if (listRequestRecord.isPresent()){

            List<BusinessEntity> businessEntities = filterBy(listRequestRecord.get());

            businessEntities.forEach(businessEntity -> businessListDTOs.add(mapper.toListDTO(businessEntity)));

        }else{

            for (BusinessEntity businessEntity : repository.findAll()){
                businessListDTOs.add(mapper.toListDTO(businessEntity));
            }
        }

        return businessListDTOs;

    }

    @Override
    public Page<BusinessListDTO> getPageableList(PageableRequest pageableRequest) throws ElementNotFoundException {

        return filterByPageable(pageableRequest);

    }


    @Override
    public BusinessListDTO updateListView(Long id) throws ElementNotFoundException {

        BusinessEntity business = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        repository.save(updateListFields(business));

        return businessMapper.toListDTO(business);

    }

    private BusinessEntity updateListFields(BusinessEntity business){

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

    private List<BusinessEntity> filterBy (FilterRequest filterRequest) throws ElementNotFoundException {

        if (filterRequest.getBy().equals("client")){

            ClientEntity client = clientRepository.findById(filterRequest.getId()).orElseThrow(ElementNotFoundException::new);

            return repository.findAllByClient(client).stream().toList();

        }else if (filterRequest.getBy().equals("plan")){

            PlanEntity plan = planRepository.findById(filterRequest.getId()).orElseThrow(ElementNotFoundException::new);

            return repository.findAllByPlan(plan).stream().toList();

        }else{
            throw new ElementNotFoundException("The filter is not valid");
        }

    }

    private Page<BusinessListDTO> filterByPageable(PageableRequest pageRequest) throws ElementNotFoundException {

        if (pageRequest.getFilter().isEmpty()){
            return repository.findAll(pageRequest.getPageRequest()).map(businessMapper::toListDTO);
        }else{

            FilterRequest filterRequest = pageRequest.getFilter().get();

            if (filterRequest.getBy().equals("client")){

                ClientEntity client = clientRepository.findById(filterRequest.getId()).orElseThrow(ElementNotFoundException::new);

                return repository.findByClient(pageRequest.getPageRequest(), client).map(businessMapper::toListDTO);

            }else if (filterRequest.getBy().equals("plan")){

                PlanEntity plan = planRepository.findById(filterRequest.getId()).orElseThrow(ElementNotFoundException::new);

                return repository.findByPlan(pageRequest.getPageRequest(), plan).map(businessMapper::toListDTO);

            }else{
                throw new ElementNotFoundException("The filter is not valid");
            }

        }

    }

    public Page<BusinessListDTO> test(String name){

        PageRequest pageRequest = PageRequest.of(0,10);

        return repository.findAll(BusinessExpressions.nameContains(name), pageRequest).map(businessMapper::toListDTO);

    }

}

package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.models.HQ.plan.PlanRepository;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BusinessServiceImplements extends DefaultServiceImplements<BusinessDTO, BusinessMiniDTO, BusinessListDTO, BusinessForm, BusinessEntity, Long> {

    private final BusinessRepository repository;

    private final ClientRepository clientRepository;

    private final PlanRepository planRepository;

    private final bsGeneralSettingsRepository bsGeneralSettingsRepository;

    private final BusinessMapper businessMapper;

    @Autowired
    public BusinessServiceImplements(BusinessRepository repository, BusinessMapper mapper,
                                     ClientRepository clientRepository,
                                     PlanRepository planRepository,
                                     bsGeneralSettingsRepository bsGeneralSettingsRepository,
                                     BusinessMapper businessMapper
    ){
        super(repository, mapper);
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.planRepository = planRepository;
        this.bsGeneralSettingsRepository = bsGeneralSettingsRepository;
        this.businessMapper = businessMapper;
    }

    @Override
    public BusinessMiniDTO insert(BusinessForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

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
        toInsert.setClientEntity(clientRepository.findById(form.getClient()).orElseThrow(ElementAlreadyExist::new));

        //Insert the Business plan.
        toInsert.setPlanEntity(planRepository.findById(form.getPlan()).orElseThrow(ElementNotFoundExeption::new));

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

    public Set<BusinessListDTO> getAllForList(Optional<Long> id) throws ElementNotFoundExeption {

        Set <BusinessListDTO> businessListDTOS = new HashSet<>();

        if (id.isPresent()){

            ClientEntity client = clientRepository.findById(id.get()).orElseThrow(ElementNotFoundExeption::new);

            for (BusinessEntity business : repository.findAllByClientEntity(client)){
                businessListDTOS.add(businessMapper.toListDTO(business));
            }

        }else{

            for (BusinessEntity businessEntity : repository.findAll()){
                businessListDTOS.add(businessMapper.toListDTO(businessEntity));
            }
        }

        return businessListDTOS;

    }

    public Page<BusinessListDTO> getPageableList(Optional<Long> id, PageableRequest pageableRequest) throws ElementNotFoundExeption {

        PageRequest pr = pageableRequest.getPageRequest();

        if (id.isEmpty()){
            return repository.findAll(pr).map(businessMapper::toListDTO);
        }

        ClientEntity client = clientRepository.findById(id.get()).orElseThrow(ElementNotFoundExeption::new);

        return repository.findByClientEntity(pr, client).map(businessMapper::toListDTO);

    }

    public BusinessListDTO updateListView(Long id) throws ElementNotFoundExeption {

        BusinessEntity business = repository.findById(id).orElseThrow(ElementNotFoundExeption::new);

        repository.save(updateListFields(business));

        return businessMapper.toListDTO(business);

    }

    private BusinessEntity updateListFields(BusinessEntity business){

        business.setInvoiceCount(business.getInvoiceEntities() == null ? 0 : (long) business.getInvoiceEntities().size());
        business.setBsClientCount(business.getBsClientEntities() == null ? 0 : (long) business.getBsClientEntities().size());
        business.setBsManagerCount(business.getBsManagerEntities() == null ? 0 : (long) business.getBsManagerEntities().size());
        business.setBsEmployeeCount(business.getBsEmployeeEntities() == null ? 0 : (long) business.getBsEmployeeEntities().size());
        business.setBsStatusCount(business.getBsStatusEntities() == null ? 0 : (long) business.getBsStatusEntities().size());
        business.setBsPriorityCount(business.getBsPriorityEntities() == null ? 0 : (long) business.getBsPriorityEntities().size());
        business.setBsPriorityCount(business.getBsPriorityEntities() == null ? 0 : (long) business.getBsPriorityEntities().size());
        business.setBsTypeCount(business.getBsTypeEntities() == null ? 0 : (long) business.getBsTypeEntities().size());
        business.setBsDocsCategoryCount(business.getBsDocsCategoryEntities() == null ? 0 : (long) business.getBsDocsCategoryEntities().size());
        business.setBsDocCount(business.getBsDocEntities() == null ? 0 : (long) business.getBsDocEntities().size());
        business.setBsKBCategoryCount(business.getBsKBCategoryEntities() == null ? 0 : (long) business.getBsKBCategoryEntities().size());
        business.setBsKBCount(business.getBsKBEntities() == null ? 0 : (long) business.getBsKBEntities().size());
        business.setBsProjectCount(business.getBsProjectEntities() == null ? 0 : (long) business.getBsProjectEntities().size());
        business.setBsTaskCategoryCount(business.getBsTaskCategoryEntities() == null ? 0 : (long) business.getBsTaskCategoryEntities().size());
        business.setBsPrTaskCount(business.getBsPrTaskEntities() == null ? 0 : (long) business.getBsPrTaskEntities().size());
        business.setBsInvoiceCount(business.getBsInvoiceEntities() == null ? 0 : (long) business.getBsInvoiceEntities().size());

        return business;
    }

}

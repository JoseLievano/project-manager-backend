package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.models.HQ.plan.PlanRepository;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class BusinessServiceImplements extends DefaultServiceImplements<BusinessDTO, BusinessMiniDTO, BusinessForm, BusinessEntity, Long> {

    private final BusinessRepository businessRepository;

    private final ClientRepository clientRepository;

    private final PlanRepository planRepository;

    private final bsGeneralSettingsRepository bsGeneralSettingsRepository;

    private final BusinessMapper businessMapper;

    @Autowired
    public BusinessServiceImplements(BusinessRepository repository, BusinessMapper mapper,
                                     BusinessRepository businessRepository,
                                     ClientRepository clientRepository,
                                     PlanRepository planRepository,
                                     bsGeneralSettingsRepository bsGeneralSettingsRepository,
                                     BusinessMapper businessMapper
    ){
        super(repository, mapper);
        this.businessRepository = businessRepository;
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
        Set<BusinessEntity> businessExistenceCheck = businessRepository.findByName(form.getName());
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

        repository.save(toInsert);

        bsGeneralSettingsRepository.save(GeneralSettingsEntity);

        return mapper.toSmallDTO(toInsert);

    }

    public Set<BusinessListDTO> getAllForList(){

        Set <BusinessListDTO> businessListDTOS = new HashSet<>();

        for (BusinessEntity businessEntity : repository.findAll()){
            businessListDTOS.add(businessMapper.toListDTO(businessEntity));
        }

        return businessListDTOS;

    }
}

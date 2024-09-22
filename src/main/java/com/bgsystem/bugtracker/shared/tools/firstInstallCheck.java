package com.bgsystem.bugtracker.shared.tools;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.admin.*;
import com.bgsystem.bugtracker.models.HQ.client.ClientForm;
import com.bgsystem.bugtracker.models.HQ.client.ClientMiniDTO;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.models.HQ.client.ClientServiceImplements;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQForm;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQMiniDTO;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQRepository;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQServiceImplements;
import com.bgsystem.bugtracker.models.HQ.plan.PlanForm;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMiniDTO;
import com.bgsystem.bugtracker.models.HQ.plan.PlanRepository;
import com.bgsystem.bugtracker.models.HQ.plan.PlanServiceImplements;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsEntity;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsRepository;
import com.bgsystem.bugtracker.models.client.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class firstInstallCheck implements ApplicationRunner {

    private final AdminRepository adminRepository;

    private final ClientRepository clientRepository;

    private final BusinessRepository businessRepository;

    private final MainHQRepository mainHQRepository;

    private final PlanRepository planRepository;

    private final bsGeneralSettingsRepository bsGeneralSettingsRepository;

    private final AdminServiceImplements adminService;

    private final ClientServiceImplements clientService;

    private final PlanServiceImplements planService;

    private final MainHQServiceImplements mainHQService;

    private final BusinessServiceImplements businessService;

    private final BusinessMapper businessMapper;

    @Autowired
    public firstInstallCheck(
            AdminRepository adminRepository,
            ClientRepository clientRepository,
            BusinessRepository businessRepository,
            MainHQRepository mainHQRepository,
            PlanRepository planRepository,
            bsGeneralSettingsRepository bsGeneralSettingsRepository,
            AdminServiceImplements adminService,
            ClientServiceImplements clientService,
            PlanServiceImplements planService,
            MainHQServiceImplements mainHQService,
            BusinessServiceImplements businessService,
            BusinessMapper businessMapper
    ){
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.businessRepository = businessRepository;
        this.mainHQRepository = mainHQRepository;
        this.planRepository = planRepository;
        this.bsGeneralSettingsRepository = bsGeneralSettingsRepository;
        this.adminService = adminService;
        this.clientService = clientService;
        this.planService = planService;
        this.mainHQService = mainHQService;
        this.businessService = businessService;
        this.businessMapper = businessMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        boolean newInstall = this.adminRepository.findAll().isEmpty() &&
                this.businessRepository.findAll().isEmpty() &&
                this.clientRepository.findAll().isEmpty() &&
                this.mainHQRepository.findAll().isEmpty();

        if (newInstall){
            this.createNewData();
        }
    }

    private void createNewData() throws ElementAlreadyExist, InvalidInsertDeails, ElementNotFoundException {

        MainHQMiniDTO newMainHQ = createMainHQ();
        System.out.println(newMainHQ.toString());

        AdminMiniDTO newAdmin = createsFirstAdmin();
        System.out.println(newAdmin.toString());

        PlanMiniDTO newPlan = createFirstPlan();
        System.out.println(newPlan.toString());

        ClientMiniDTO newClient = createFirstClient();
        System.out.println(newClient.toString());

        System.out.println("Before set Business");
        BusinessMiniDTO newBusiness = createFirstBusiness(newClient, newPlan);
        System.out.println(newBusiness.toString());
    }

    private MainHQMiniDTO createMainHQ() throws ElementAlreadyExist, InvalidInsertDeails, ElementNotFoundException {
        MainHQForm mainHQForm = new MainHQForm();
        mainHQForm.setName("Main HQ");

        return mainHQService.insert(mainHQForm);
    }

    private AdminMiniDTO createsFirstAdmin() throws ElementAlreadyExist, InvalidInsertDeails, ElementNotFoundException {

        //Creates new admin form
        AdminForm adminForm = new AdminForm();
        adminForm.setEmail("x@x.com");
        adminForm.setFirstName("X");
        adminForm.setLastName("K");
        adminForm.setUsername("admin");
        adminForm.setPassword("test");

        //Insert the new admin using admin service
        return adminService.insert(adminForm);
    }

    private ClientMiniDTO createFirstClient() throws ElementAlreadyExist, InvalidInsertDeails, ElementNotFoundException {

        //Create client form
        ClientForm clientForm = new ClientForm();
        clientForm.setEmail("client@tracker.com");
        clientForm.setFirstName("Nathaly");
        clientForm.setLastName("Parras");
        clientForm.setUsername("naty");
        clientForm.setPassword("test");

        return clientService.insert(clientForm);
    }

    private PlanMiniDTO createFirstPlan() throws ElementAlreadyExist, InvalidInsertDeails, ElementNotFoundException {

        //Create plan form
        PlanForm planForm = new PlanForm();
        planForm.setName("First Plan");
        planForm.setPrice(9.99);

        return planService.insert(planForm);

    }

    private BusinessMiniDTO createFirstBusiness(ClientMiniDTO client, PlanMiniDTO plan) throws ElementAlreadyExist, InvalidInsertDeails, ElementNotFoundException{

        BusinessForm businessForm = new BusinessForm();
        businessForm.setName("First Business");
        businessForm.setTaxID("BE093258");
        businessForm.setClient(client.getId());
        businessForm.setPlan(plan.getId());

        Set<BusinessEntity> businessExistenceCheck = businessRepository.findByName(businessForm.getName());
        if (!businessExistenceCheck.isEmpty())
            throw new ElementAlreadyExist("Business already exist");

        BusinessEntity toInsert = businessMapper.toEntity(businessForm);

        toInsert.setClient(clientRepository.findById(businessForm.getClient()).orElseThrow(ElementAlreadyExist::new));

        toInsert.setPlan(planRepository.findById(businessForm.getPlan()).orElseThrow(ElementAlreadyExist::new));

        bsGeneralSettingsEntity GeneralSettingsEntity = bsGeneralSettingsEntity.builder()
                .address("Demo Adress")
                .email("info@demo-first-business.com")
                .website("www.demo-first-business-web.com")
                .business(toInsert)
                .build();

        toInsert.setBsGeneralSettings(GeneralSettingsEntity);

        businessRepository.save(businessService.updateListFields(toInsert));

        bsGeneralSettingsRepository.save(GeneralSettingsEntity);

        return businessMapper.toSmallDTO(toInsert);
    }

}

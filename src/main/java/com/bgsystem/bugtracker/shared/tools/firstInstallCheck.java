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
import com.bgsystem.bugtracker.models.HQ.plan.PlanServiceImplements;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class firstInstallCheck implements ApplicationRunner {

    private final AdminRepository adminRepository;

    private final ClientRepository clientRepository;

    private final BusinessRepository businessRepository;

    private final MainHQRepository mainHQRepository;

    private final AdminServiceImplements adminService;

    private final ClientServiceImplements clientService;

    private final PlanServiceImplements planService;

    private final MainHQServiceImplements mainHQService;

    @Autowired
    public firstInstallCheck(
            AdminRepository adminRepository,
            ClientRepository clientRepository,
            BusinessRepository businessRepository,
            MainHQRepository mainHQRepository,
            AdminServiceImplements adminService,
            ClientServiceImplements clientService,
            PlanServiceImplements planService,
            MainHQServiceImplements mainHQService
    ){
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.businessRepository = businessRepository;
        this.mainHQRepository = mainHQRepository;
        this.adminService = adminService;
        this.clientService = clientService;
        this.planService = planService;
        this.mainHQService = mainHQService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        boolean newInstall =    this.adminRepository.findAll().size() == 0 &&
                                this.businessRepository.findAll().size() == 0 &&
                                this.clientRepository.findAll().size() == 0 &&
                                this.mainHQRepository.findAll().size() == 0;

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
        planForm.setPrice(9.99d);

        return planService.insert(planForm);

    }

}

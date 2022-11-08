package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.models.HQ.admin.AdminRepository;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.models.HQ.employee.EmployeeRepository;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceRepository;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQRepository;
import com.bgsystem.bugtracker.models.HQ.plan.PlanRepository;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientRepository;
import com.bgsystem.bugtracker.models.client.bsDoc.bsDocRepository;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryRepository;
import com.bgsystem.bugtracker.models.client.bsEmployee.bsEmployeeRepository;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceRepository;
import com.bgsystem.bugtracker.models.client.bsKB.bsKBRepository;
import com.bgsystem.bugtracker.models.client.bsKBCategory.bsKBCategoryRepository;
import com.bgsystem.bugtracker.models.client.bsManager.bsManagerRepository;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityRepository;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusRepository;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryRepository;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrKB.bsPrKBRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskRepository;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntityFactory {

    private final AdminRepository adminRepository;

    private final ClientRepository clientRepository;

    private final EmployeeRepository employeeRepository;

    private final InvoiceRepository invoiceRepository;

    private final MainHQRepository mainHQRepository;

    private final PlanRepository planRepository;

    private final bsClientRepository bsClientRepository;

    private final bsDocRepository bsDocRepository;

    private final bsDocsCategoryRepository bsDocCategoryRepository;

    private final bsEmployeeRepository bsEmployeeRepository;

    private final bsInvoiceRepository bsInvoiceRepository;

    private final bsKBRepository bsKBRepository;

    private final bsKBCategoryRepository bsKBCategoryRepository;

    private final bsManagerRepository bsManagerRepository;

    private final bsPriorityRepository bsPriorityRepository;

    private final bsStatusRepository bsStatusRepository;

    private final bsTaskCategoryRepository bsTaskCategoryRepository;

    private final bsTypeRepository bsTyperepository;

    private final BusinessRepository businessRepository;

    private final bsPrChannelRepository bsPrChannelRepository;

    private final bsPrCommentRepository bsPrCommentRepository;

    private final bsPrDocsRepository bsPrDocsRepository;

    private final bsPrKBRepository bsPRKbRepository;

    private final bsPrKBCategoryRepository bsPrKBCategoryRepository;

    private final bsProjectRepository bsProjectRepository;

    private final bsPrTaskRepository bsPrTaskRepository;

    @Autowired
    public EntityFactory(
            AdminRepository adminRepository,
            ClientRepository clientRepository,
            EmployeeRepository employeeRepository,
            InvoiceRepository invoiceRepository,
            MainHQRepository mainHQRepository,
            PlanRepository planRepository,
            bsClientRepository bsClientRepository,
            bsDocRepository bsDocRepository,
            bsDocsCategoryRepository bsDocCategoryRepository,
            bsEmployeeRepository bsEmployeeRepository,
            bsInvoiceRepository bsInvoiceRepository,
            bsKBRepository bsKBRepository,
            bsKBCategoryRepository bsKBCategoryRepository,
            bsManagerRepository bsManagerRepository,
            bsPriorityRepository bsPriorityRepository,
            bsStatusRepository bsStatusRepository,
            bsTaskCategoryRepository bsTaskCategoryRepository,
            bsTypeRepository bsTyperepository,
            BusinessRepository businessRepository,
            bsPrChannelRepository bsPrChannelRepository,
            bsPrCommentRepository bsPrCommentRepository,
            bsPrDocsRepository bsPrDocsRepository,
            bsPrKBRepository bsPRKbRepository,
            bsPrKBCategoryRepository bsPrKBCategoryRepository,
            bsProjectRepository bsProjectRepository,
            bsPrTaskRepository bsPrTaskRepository
    ) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.invoiceRepository = invoiceRepository;
        this.mainHQRepository = mainHQRepository;
        this.planRepository = planRepository;
        this.bsClientRepository = bsClientRepository;
        this.bsDocRepository = bsDocRepository;
        this.bsDocCategoryRepository = bsDocCategoryRepository;
        this.bsEmployeeRepository = bsEmployeeRepository;
        this.bsInvoiceRepository = bsInvoiceRepository;
        this.bsKBRepository = bsKBRepository;
        this.bsKBCategoryRepository = bsKBCategoryRepository;
        this.bsManagerRepository = bsManagerRepository;
        this.bsPriorityRepository = bsPriorityRepository;
        this.bsStatusRepository = bsStatusRepository;
        this.bsTaskCategoryRepository = bsTaskCategoryRepository;
        this.bsTyperepository = bsTyperepository;
        this.businessRepository = businessRepository;
        this.bsPrChannelRepository = bsPrChannelRepository;
        this.bsPrCommentRepository = bsPrCommentRepository;
        this.bsPrDocsRepository = bsPrDocsRepository;
        this.bsPRKbRepository = bsPRKbRepository;
        this.bsPrKBCategoryRepository = bsPrKBCategoryRepository;
        this.bsProjectRepository = bsProjectRepository;
        this.bsPrTaskRepository = bsPrTaskRepository;
    }

    public <T> T getEntity(Long id, String className) {
        switch (className) {
            case "Admin":
                return (T) adminRepository.findById(id);
            case "Client":
                return (T) clientRepository.findById(id);
            case "Employee":
                return (T) employeeRepository.findById(id);
            case "Invoice":
                return (T) invoiceRepository.findById(id);
            case "MainHQ":
                return (T) mainHQRepository.findById(id);
            case "Plan":
                return (T) planRepository.findById(id);
            case "bsClient":
                return (T) bsClientRepository.findById(id);
            case "bsDoc":
                return (T) bsDocRepository.findById(id);
            case "bsDocsCategory":
                return (T) bsDocCategoryRepository.findById(id);
            case "bsEmployee":
                return (T) bsEmployeeRepository.findById(id);
            case "bsInvoice":
                return (T) bsInvoiceRepository.findById(id);
            case "bsKB":
                return (T) bsKBRepository.findById(id);
            case "bsKBCategory":
                return (T) bsKBCategoryRepository.findById(id);
            case "bsManager":
                return (T) bsManagerRepository.findById(id);
            case "bsPriority":
                return (T) bsPriorityRepository.findById(id);
            case "bsStatus":
                return (T) bsStatusRepository.findById(id);
            case "bsTaskCategory":
                return (T) bsTaskCategoryRepository.findById(id);
            case "bsType":
                return (T) bsTyperepository.findById(id);
            case "Business":
                return (T) businessRepository.findById(id);
            case "bsPrChannel":
                return (T) bsPrChannelRepository.findById(id);
            case "bsPrComment":
                return (T) bsPrCommentRepository.findById(id);
            case "bsPrDocs":
                return (T) bsPrDocsRepository.findById(id);
            case "bsPrKB":
                return (T) bsPRKbRepository.findById(id);
            case "bsPrKBCategory":
                return (T) bsPrKBCategoryRepository.findById(id);
            case "bsProject":
                return (T) bsProjectRepository.findById(id);
            case "bsPrTask":
                return (T) bsPrTaskRepository.findById(id);
            default:
                return null;
        }
    }
}

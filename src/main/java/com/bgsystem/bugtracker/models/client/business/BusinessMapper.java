package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientMapper;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMapper;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMapper;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientMapper;
import com.bgsystem.bugtracker.models.client.bsDoc.bsDocMapper;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryMapper;
import com.bgsystem.bugtracker.models.client.bsEmployee.bsEmployeeMapper;
import com.bgsystem.bugtracker.models.client.bsGeneralSettings.bsGeneralSettingsMapper;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMapper;
import com.bgsystem.bugtracker.models.client.bsKB.bsKBMapper;
import com.bgsystem.bugtracker.models.client.bsKBCategory.bsKBCategoryMapper;
import com.bgsystem.bugtracker.models.client.bsManager.bsManagerMapper;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityMapper;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusMapper;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryMapper;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BusinessMapper implements DefaultMapper <BusinessDTO, BusinessMiniDTO, BusinessListDTO, BusinessForm, BusinessEntity> {

    private final ClientMapper clientMapper;

    private final InvoiceMapper invoiceMapper;

    private final PlanMapper planMapper;

    private final bsClientMapper bsClientMapper;

    private final bsGeneralSettingsMapper bsGeneralSettingsMapper;

    private final bsManagerMapper bsManagerMapper;

    private final bsEmployeeMapper bsEmployeeMapper;

    private final bsStatusMapper bsStatusMapper;

    private final bsPriorityMapper bsPriorityMapper;

    private final bsTypeMapper bsTypeMapper;

    private final bsDocsCategoryMapper bsDocsCategoryMapper;

    private final bsDocMapper bsDocMapper;

    private final bsKBCategoryMapper bsKBCategoryMapper;

    private final bsKBMapper bsKBMapper;

    private final bsProjectMapper bsProjectMapper;

    private final bsTaskCategoryMapper bsTaskCategoryMapper;

    private final bsPrTaskMapper bsPrTaskMapper;

    private final bsInvoiceMapper bsInvoiceMapper;

    @Lazy
    @Autowired
    public BusinessMapper(
            ClientMapper clientMapper,
            InvoiceMapper invoiceMapper,
            PlanMapper planMapper,
            bsClientMapper bsClientMapper,
            bsGeneralSettingsMapper bsGeneralSettingsMapper,
            bsManagerMapper bsManagerMapper,
            bsEmployeeMapper bsEmployeeMapper,
            bsStatusMapper bsStatusMapper,
            bsPriorityMapper bsPriorityMapper,
            bsTypeMapper bsTypeMapper,
            bsDocsCategoryMapper bsDocsCategoryMapper,
            bsDocMapper bsDocMapper,
            bsKBCategoryMapper bsKBCategoryMapper,
            bsKBMapper bsKBMapper,
            bsProjectMapper bsProjectMapper,
            bsTaskCategoryMapper bsTaskCategoryMapper,
            bsPrTaskMapper bsPrTaskMapper,
            bsInvoiceMapper bsInvoiceMapper
    ) {
        this.clientMapper = clientMapper;
        this.invoiceMapper = invoiceMapper;
        this.planMapper = planMapper;
        this.bsClientMapper = bsClientMapper;
        this.bsGeneralSettingsMapper = bsGeneralSettingsMapper;
        this.bsManagerMapper = bsManagerMapper;
        this.bsEmployeeMapper = bsEmployeeMapper;
        this.bsStatusMapper = bsStatusMapper;
        this.bsPriorityMapper = bsPriorityMapper;
        this.bsTypeMapper = bsTypeMapper;
        this.bsDocsCategoryMapper = bsDocsCategoryMapper;
        this.bsDocMapper = bsDocMapper;
        this.bsKBCategoryMapper = bsKBCategoryMapper;
        this.bsKBMapper = bsKBMapper;
        this.bsProjectMapper = bsProjectMapper;
        this.bsTaskCategoryMapper = bsTaskCategoryMapper;
        this.bsPrTaskMapper = bsPrTaskMapper;
        this.bsInvoiceMapper = bsInvoiceMapper;
    }

    @Override
    public BusinessDTO toDTO(BusinessEntity entity) {

        if (entity == null)
            return null;

        return BusinessDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .taxID(entity.getTaxID())
                .taxID(entity.getTaxID())
                .dateCreated(entity.getDateCreated())
                .pendingInvoice(entity.getPendingInvoice())
                .overDue(entity.getOverDue())
                .isActive(entity.getIsActive())
                .client(clientMapper.toSmallDTO(entity.getClient()))
                .invoices(entity.getInvoices()
                        .stream()
                        .map(invoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsClients(entity.getBsClients()
                        .stream()
                        .map(bsClientMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsManagers(entity.getBsManagers()
                        .stream()
                        .map(bsManagerMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsEmployees(entity.getBsEmployees()
                        .stream()
                        .map(bsEmployeeMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsStatuses(entity.getBsStatuses()
                        .stream()
                        .map(bsStatusMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsPriorities(entity.getBsPriorities()
                        .stream()
                        .map(bsPriorityMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsTypes(entity.getBsTypes()
                        .stream()
                        .map(bsTypeMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsDocsCategories(entity.getBsDocsCategories()
                        .stream()
                        .map(bsDocsCategoryMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsDocs(entity.getBsDocs()
                        .stream()
                        .map(bsDocMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsKBCategories(entity.getBsKBCategories()
                        .stream()
                        .map(bsKBCategoryMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsKBs(entity.getBsKBs()
                        .stream()
                        .map(bsKBMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsProjects(entity.getBsProjects()
                        .stream()
                        .map(bsProjectMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsTaskCategories(entity.getBsTaskCategories()
                        .stream()
                        .map(bsTaskCategoryMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsPrTasks(entity.getBsPrTasks()
                        .stream()
                        .map(bsPrTaskMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsInvoices(entity.getBsInvoices()
                        .stream()
                        .map(bsInvoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .bsGeneralSettings(bsGeneralSettingsMapper.toSmallDTO(entity.getBsGeneralSettings()))
                .plan(planMapper.toSmallDTO(entity.getPlan()))
                .build();

    }

    @Override
    public BusinessMiniDTO toSmallDTO(BusinessEntity entity) {

        if (entity == null)
            return null;

        return BusinessMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .taxID(entity.getTaxID())
                .dateCreated(entity.getDateCreated())
                .pendingInvoice(entity.getPendingInvoice())
                .overDue(entity.getOverDue())
                .isActive(entity.getIsActive())
                .build();

    }

    @Override
    public BusinessEntity toEntity(BusinessForm form) {

        if (form == null)
            return null;

        return BusinessEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .taxID(form.getTaxID())
                .build();
    }

    public BusinessListDTO toListDTO(BusinessEntity entity){

        if (entity == null)
            return null;

        return BusinessListDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .taxID(entity.getTaxID())
                .dateCreated(entity.getDateCreated())
                .pendingInvoice(entity.getPendingInvoice())
                .overDue(entity.getOverDue())
                .isActive(entity.getIsActive())
                .client(clientMapper.toSmallDTO(entity.getClient()))
                .plan(planMapper.toSmallDTO(entity.getPlan()))
                .invoices(entity.getBsInvoiceCount())
                .bsClients(entity.getBsClientCount())
                .bsManagers(entity.getBsManagerCount())
                .bsEmployees(entity.getBsEmployeeCount())
                .bsStatuses(entity.getBsStatusCount())
                .bsPriorities(entity.getBsPriorityCount())
                .bsTypes(entity.getBsTypeCount())
                .bsDocsCategories(entity.getBsDocsCategoryCount())
                .bsDocs(entity.getBsDocCount())
                .bsKBCategories(entity.getBsKBCategoryCount())
                .bsKBs(entity.getBsKBCount())
                .bsProjects(entity.getBsProjectCount())
                .bsTaskCategories(entity.getBsTaskCategoryCount())
                .bsPrTasks(entity.getBsPrTaskCount())
                .bsInvoices(entity.getBsInvoiceCount())
                .build();

    }

}

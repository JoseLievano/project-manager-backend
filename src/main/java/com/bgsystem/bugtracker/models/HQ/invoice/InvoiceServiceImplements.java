package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQRepository;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class InvoiceServiceImplements extends DefaultServiceImplements<InvoiceDTO, InvoiceMiniDTO, InvoiceListDTO, InvoiceForm, InvoiceEntity, Long> {

    private final InvoiceRepository invoiceRepository;

    private final MainHQRepository mainHQRepository;

    private final ClientRepository clientRepository;

    private final BusinessRepository businessRepository;

    private final PlanRepository planRepository;

    private final InvoicePredicate invoicePredicate;

    @Autowired
    public InvoiceServiceImplements(
                                    InvoiceRepository repository,
                                    InvoiceMapper mapper,
                                    MainHQRepository mainHQRepository,
                                    ClientRepository clientRepository,
                                    BusinessRepository businessRepository,
                                    PlanRepository planRepository,
                                    InvoicePredicate invoicePredicate
    ) {
        super(repository, mapper, invoicePredicate);
        this.invoiceRepository = repository;
        this.mainHQRepository = mainHQRepository;
        this.clientRepository = clientRepository;
        this.businessRepository = businessRepository;
        this.planRepository = planRepository;
        this.invoicePredicate = invoicePredicate;
    }

    @Override
    public InvoiceMiniDTO insert(InvoiceForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        //Validating the form information
        if (form == null || form.getNumber() == null || form.getBusiness() == null || form.getClient() == null || form.getPlan() == null){
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new invoice");
        }

        //Check if the invoice already exist in our DB
        Set<InvoiceEntity> invoiceExistenceCheck = invoiceRepository.findByNumber(form.getNumber());
        if (invoiceExistenceCheck.size() > 0) {
            throw new ElementAlreadyExist("The invoice already exist in our DB");
        }

        InvoiceEntity toInsert = mapper.toEntity(form);

        //Insert the MainHQ in the invoice
        MainHQEntity mainHQEntity = mainHQRepository.findAll().get(0);
        if (mainHQEntity == null) {
            throw new ElementNotFoundException("The MainHQ is not found in our DB");
        }else {
            toInsert.setMainHQEntity(mainHQEntity);
        }

        //Set invoice price based on the plan selected
        PlanEntity planEntity = planRepository.findById(form.getPlan()).orElseThrow(ElementNotFoundException::new);
        toInsert.setPlanEntity(planEntity);
        toInsert.setAmount(planEntity.getPrice());

        planEntity.getInvoiceEntities().add(toInsert);
        planRepository.save(planEntity);

        //Set invoice client based on the client selected
        ClientEntity clientEntity = clientRepository.findById(form.getClient()).orElseThrow(ElementNotFoundException::new);
        toInsert.setClientEntity(clientEntity);
        clientEntity.getInvoiceEntities().add(toInsert);
        clientRepository.save(clientEntity);

        //Set invoice business based on the business selected
        BusinessEntity businessEntity = businessRepository.findById(form.getBusiness()).orElseThrow(ElementNotFoundException::new);
        toInsert.setBusinessEntity(businessEntity);
        businessEntity.getInvoices().add(toInsert);
        businessRepository.save(businessEntity);

        //Set the generated date of the invoice
        toInsert.setDateGenerated(new Date());

        //Set limit date of the invoice, sum 5 days to todays date
        toInsert.setLimitDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 5)));

        toInsert.setIsPaid(false);

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }
}
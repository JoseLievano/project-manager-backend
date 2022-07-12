package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InvoiceServiceImplements extends DefaultServiceImplements<InvoiceDTO, InvoiceMiniDTO, InvoiceForm, InvoiceEntity, Long> {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImplements(InvoiceRepository repository, InvoiceMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public InvoiceMiniDTO insert(InvoiceForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

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

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }
}
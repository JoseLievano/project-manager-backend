package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController extends DefaultController<InvoiceDTO, InvoiceMiniDTO, InvoiceListDTO, InvoiceForm, Long> {

    public InvoiceController(DefaultService<InvoiceDTO, InvoiceMiniDTO, InvoiceListDTO, InvoiceForm, Long> service) {
        super(service);
    }

}

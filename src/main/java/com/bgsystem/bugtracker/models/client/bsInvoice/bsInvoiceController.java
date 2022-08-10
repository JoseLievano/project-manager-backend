package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_invoice")
public class bsInvoiceController extends DefaultController <bsInvoiceDTO, bsInvoiceMiniDTO, bsInvoiceForm, Long> {

    public bsInvoiceController ( bsInvoiceServiceImplements service){
        super (service);
    }

}

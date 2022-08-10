package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsInvoiceMapper implements DefaultMapper <bsInvoiceDTO, bsInvoiceMiniDTO, bsInvoiceForm, bsInvoiceEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Lazy
    @Autowired
    private bsClientMapper clientMapper;

    @Lazy
    @Autowired
    private bsProjectMapper projectMapper;

    @Lazy
    @Autowired
    private bsPrTaskMapper taskMapper;

    @Override
    public bsInvoiceDTO toDTO(bsInvoiceEntity entity) {
        return null;
    }

    @Override
    public bsInvoiceMiniDTO toSmallDTO(bsInvoiceEntity entity) {
        return null;
    }

    @Override
    public bsInvoiceEntity toEntity(bsInvoiceForm form) {
        return null;
    }
}

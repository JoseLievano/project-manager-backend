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

        if (entity == null){
            return null;
        }

        return bsInvoiceDTO.builder()
                .id(entity.getId())
                .dateGenerated(entity.getDateGenerated())
                .limitDate(entity.getLimitDate())
                .amount(entity.getAmount())
                .isPaid(entity.getIsPaid())
                .isOverDue(entity.getIsOverDue())
                .number(entity.getNumber())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .client(clientMapper.toSmallDTO(entity.getClient()))
                .project(projectMapper.toSmallDTO(entity.getProject()))
                .task(taskMapper.toSmallDTO(entity.getTask()))
                .build();

    }

    @Override
    public bsInvoiceMiniDTO toSmallDTO(bsInvoiceEntity entity) {

        if (entity == null){
            return null;
        }

        return bsInvoiceMiniDTO.builder()
                .id(entity.getId())
                .dateGenerated(entity.getDateGenerated())
                .limitDate(entity.getLimitDate())
                .amount(entity.getAmount())
                .isPaid(entity.getIsPaid())
                .isOverDue(entity.getIsOverDue())
                .number(entity.getNumber())
                .build();
    }

    @Override
    public bsInvoiceEntity toEntity(bsInvoiceForm form) {

        if (form == null){
            return null;
        }

        return bsInvoiceEntity.builder()
                .id(form.getId())
                .dateGenerated(form.getDateGenerated())
                .limitDate(form.getLimitDate())
                .amount(form.getAmount())
                .isPaid(form.getIsPaid())
                .isOverDue(form.getIsOverDue())
                .number(form.getNumber())
                .build();
    }
}

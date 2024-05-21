package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.models.HQ.client.ClientMapper;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class InvoiceMapper implements DefaultMapper <InvoiceDTO, InvoiceMiniDTO, InvoiceListDTO, InvoiceForm, InvoiceEntity>{

    @Lazy
    @Autowired
    private ClientMapper clientMapper;

    @Lazy
    @Autowired
    private PlanMapper planMapper;

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public InvoiceDTO toDTO(InvoiceEntity entity) {

        if (entity == null)
            return null;

        return InvoiceDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .dateGenerated(entity.getDateGenerated())
                .limitDate(entity.getLimitDate())
                .isPaid(entity.getIsPaid())
                .overDue(entity.getOverDue())
                .number(entity.getNumber())
                .plan(planMapper.toSmallDTO(entity.getPlanEntity()))
                .client(clientMapper.toSmallDTO(entity.getClientEntity()))
                .business(businessMapper.toSmallDTO(entity.getBusinessEntity()))
                .build();
    }

    @Override
    public InvoiceMiniDTO toSmallDTO(InvoiceEntity entity) {

        if (entity == null)
            return null;

        return InvoiceMiniDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .dateGenerated(entity.getDateGenerated())
                .limitDate(entity.getLimitDate())
                .isPaid(entity.getIsPaid())
                .overDue(entity.getOverDue())
                .number(entity.getNumber())
                .build();
    }

    @Override
    public InvoiceEntity toEntity(InvoiceForm form) {

        if (form == null)
            return null;

        return InvoiceEntity.builder()
                .id(form.getId())
                .amount(form.getAmount())
                .dateGenerated(form.getDateGenerated())
                .limitDate(form.getLimitDate())
                .isPaid(form.getIsPaid())
                .number(form.getNumber())
                .build();
    }

    @Override
    public InvoiceListDTO toListDTO(InvoiceEntity invoiceEntity) {

        if (invoiceEntity == null)
            return null;

        return InvoiceListDTO.builder()
                .id(invoiceEntity.getId())
                .amount(invoiceEntity.getAmount())
                .dateGenerated(invoiceEntity.getDateGenerated())
                .limitDate(invoiceEntity.getLimitDate())
                .isPaid(invoiceEntity.getIsPaid())
                .overDue(invoiceEntity.getOverDue())
                .number(invoiceEntity.getNumber())
                .plan(planMapper.toSmallDTO(invoiceEntity.getPlanEntity()))
                .client(clientMapper.toSmallDTO(invoiceEntity.getClientEntity()))
                .business(businessMapper.toSmallDTO(invoiceEntity.getBusinessEntity()))
                .build();

    }
}

package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientMapper;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMapper;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BusinessMapper implements DefaultMapper <BusinessDTO, BusinessMiniDTO, BusinessForm, BusinessEntity> {

    @Lazy
    @Autowired
    private ClientMapper clientMapper;

    @Lazy
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Lazy
    @Autowired
    private PlanMapper planMapper;

    @Override
    public BusinessDTO toDTO(BusinessEntity entity) {

        if (entity == null)
            return null;

        return BusinessDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .taxID(entity.getTaxID())
                .client(clientMapper.toSmallDTO(entity.getClientEntity()))
                .invoices(entity.getInvoiceEntities()
                        .stream()
                        .map(invoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .plan(planMapper.toSmallDTO(entity.getPlanEntity()))
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
}

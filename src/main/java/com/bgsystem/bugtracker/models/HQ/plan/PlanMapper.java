package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PlanMapper implements DefaultMapper <PlanDTO, PlanMiniDTO, PlanForm, PlanEntity>{

    @Lazy
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;


    @Override
    public PlanDTO toDTO(PlanEntity entity) {
        if (entity == null)
            return null;

        return PlanDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .userLimit(entity.getUserLimit())
                .diskLimit(entity.getDiskLimit())
                .maxProjects(entity.getMaxProjects())
                .invoices(entity.getInvoiceEntities()
                        .stream()
                        .map(invoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .businesses(entity.getBusinessEntities()
                        .stream()
                        .map(businessMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public PlanMiniDTO toSmallDTO(PlanEntity entity) {

        if (entity == null)
            return null;

        return PlanMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .userLimit(entity.getUserLimit())
                .diskLimit(entity.getDiskLimit())
                .maxProjects(entity.getMaxProjects())
                .build();
    }

    @Override
    public PlanEntity toEntity(PlanForm form) {
        if (form == null)
            return null;

        return PlanEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .price(form.getPrice())
                .userLimit(form.getUserLimit())
                .diskLimit(form.getDiskLimit())
                .maxProjects(form.getMaxProjects())
                .build();
    }
}

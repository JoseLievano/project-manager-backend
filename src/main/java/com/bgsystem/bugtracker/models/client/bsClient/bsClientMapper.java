package com.bgsystem.bugtracker.models.client.bsClient;

import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsClientMapper implements DefaultMapper <bsClientDTO, bsClientMiniDTO, bsClientListDTO, bsClientForm, bsClientEntity> {

    private final BusinessMapper businessMapper;

    private final bsProjectMapper bsProjectMapper;

    private final bsInvoiceMapper bsInvoiceMapper;

    @Lazy
    @Autowired
    public bsClientMapper(BusinessMapper businessMapper, bsProjectMapper bsProjectMapper, bsInvoiceMapper bsInvoiceMapper) {
        this.businessMapper = businessMapper;
        this.bsProjectMapper = bsProjectMapper;
        this.bsInvoiceMapper = bsInvoiceMapper;
    }

    @Override
    public bsClientDTO toDTO(bsClientEntity entity) {

        if (entity == null)
            return null;

        return bsClientDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .username(entity.getUsername())
                .isActive(entity.getIsActive())
                .dateCreated(entity.getDateCreated())
                .lastLoginDate(entity.getLastLogin())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .projects(entity.getProjects()
                        .stream()
                        .map(bsProjectMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .invoices(entity.getInvoices()
                        .stream()
                        .map(bsInvoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();

    }

    @Override
    public bsClientMiniDTO toSmallDTO(bsClientEntity entity) {

        if (entity == null)
            return null;

        return bsClientMiniDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .isActive(entity.getIsActive())
                .dateCreated(entity.getDateCreated())
                .lastLoginDate(entity.getLastLogin())
                .build();

    }

    @Override
    public bsClientEntity toEntity(bsClientForm form) {

        if (form == null)
            return null;

        return bsClientEntity.bsClientBuilder()
                .id(form.getId())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .username(form.getUsername())
                .password(form.getPassword())
                .isActive(form.getIsActive())
                .dateCreated(form.getDateCreated())
                .lastLogin(form.getLastLoginDate())
                .build();
    }

    @Override
    public bsClientListDTO toListDTO(bsClientEntity bsClientEntity) {

        if (bsClientEntity == null)
            return null;

        return bsClientListDTO.builder()
                .id(bsClientEntity.getId())
                .firstName(bsClientEntity.getFirstName())
                .lastName(bsClientEntity.getLastName())
                .email(bsClientEntity.getEmail())
                .username(bsClientEntity.getUsername())
                .isActive(bsClientEntity.getIsActive())
                .dateCreated(bsClientEntity.getDateCreated())
                .lastLoginDate(bsClientEntity.getLastLogin())
                .projectsCount(bsClientEntity.getProjectsCount())
                .invoicesCount(bsClientEntity.getInvoicesCount())
                .build();

    }
}

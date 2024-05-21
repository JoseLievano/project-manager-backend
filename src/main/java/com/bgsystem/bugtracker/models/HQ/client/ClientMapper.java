package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClientMapper implements DefaultMapper <ClientDTO, ClientMiniDTO, ClientListDTO, ClientForm, ClientEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Lazy
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Lazy
    @Override
    public ClientDTO toDTO(ClientEntity entity) {
        if (entity == null)
            return null;

        return ClientDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .username(entity.getUsername())
                .isActive(entity.getIsActive())
                .dateCreated(entity.getDateCreated())
                .lastLoginDate(entity.getLastLoginDate())
                .businesses(entity.getBusinessEntities()
                        .stream()
                        .map(businessMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .invoice(entity.getInvoiceEntities()
                        .stream()
                        .map(invoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public ClientMiniDTO toSmallDTO(ClientEntity entity) {

        if (entity == null)
            return null;

        return ClientMiniDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .username(entity.getUsername())
                .isActive(entity.getIsActive())
                .dateCreated(entity.getDateCreated())
                .lastLoginDate(entity.getLastLoginDate())
                .build();
    }

    @Override
    public ClientEntity toEntity(ClientForm form) {

        if (form == null)
            return null;

        return ClientEntity.clientBuilder()
                .id(form.getId())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .username(form.getUsername())
                .password(form.getPassword())
                .isActive(form.getIsActive())
                .dateCreated(form.getDateCreated())
                .lastLoginDate(form.getLastLoginDate())
                .build();

    }

    @Override
    public ClientListDTO toListDTO(ClientEntity clientEntity) {

        if (clientEntity == null)
            return null;

        return ClientListDTO.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .email(clientEntity.getEmail())
                .roles(clientEntity.getRoles())
                .username(clientEntity.getUsername())
                .isActive(clientEntity.getIsActive())
                .dateCreated(clientEntity.getDateCreated())
                .lastLoginDate(clientEntity.getLastLoginDate())
                .businessCount(clientEntity.getBusinessCount())
                .invoiceCount(clientEntity.getInvoiceCount())
                .build();

    }
}

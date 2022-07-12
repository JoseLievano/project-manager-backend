package com.bgsystem.bugtracker.models.HQ.admin;

import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminMapper implements DefaultMapper <AdminDTO, AdminMiniDTO, AdminForm, AdminEntity> {

    @Override
    public AdminDTO toDTO(AdminEntity adminEntity) {
        if (adminEntity == null)
            return null;

        return AdminDTO.builder()
                .id(adminEntity.getId())
                .firstName(adminEntity.getFirstName())
                .lastName(adminEntity.getLastName())
                .email(adminEntity.getEmail())
                .username(adminEntity.getUsername())
                .roles(adminEntity.getRoles())
                .build();
    }

    @Override
    public AdminMiniDTO toSmallDTO(AdminEntity adminEntity) {
        if (adminEntity == null)
            return null;

        return AdminMiniDTO.builder()
                .id(adminEntity.getId())
                .firstName(adminEntity.getFirstName())
                .email(adminEntity.getEmail())
                .username(adminEntity.getUsername())
                .roles(adminEntity.getRoles())
                .build();
    }

    @Override
    public AdminEntity toEntity(AdminForm adminForm) {
        if (adminForm == null)
            return null;

        return AdminEntity.adminBuilder()
                .id(adminForm.getId())
                .firstName(adminForm.getFirstName())
                .lastName(adminForm.getLastName())
                .email(adminForm.getEmail())
                .username(adminForm.getUsername())
                .build();
    }
}

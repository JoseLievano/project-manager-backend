package com.bgsystem.bugtracker.models.HQ.employee;

import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper implements DefaultMapper <EmployeeDTO, EmployeeMiniDTO, EmployeeForm, EmployeeEntity> {

    @Override
    public EmployeeDTO toDTO(EmployeeEntity entity) {
        if (entity == null)
            return null;

        return EmployeeDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .build();
    }

    @Override
    public EmployeeMiniDTO toSmallDTO(EmployeeEntity entity) {
        if (entity == null)
            return null;

        return EmployeeMiniDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .build();

    }

    @Override
    public EmployeeEntity toEntity(EmployeeForm form) {

        if (form == null)
            return null;

        return EmployeeEntity.employeeBuilder()
                .id(form.getId())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .username(form.getUsername())
                .password(form.getPassword())
                .email(form.getEmail())
                .build();
    }
}

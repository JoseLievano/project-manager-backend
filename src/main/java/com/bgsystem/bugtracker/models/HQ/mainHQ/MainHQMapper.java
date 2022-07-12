package com.bgsystem.bugtracker.models.HQ.mainHQ;

import com.bgsystem.bugtracker.models.HQ.client.ClientMapper;
import com.bgsystem.bugtracker.models.HQ.employee.EmployeeMapper;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMapper;
import com.bgsystem.bugtracker.models.HQ.plan.PlanMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MainHQMapper implements DefaultMapper<MainHQDTO, MainHQMiniDTO, MainHQForm, MainHQEntity> {

    @Lazy
    @Autowired
    private PlanMapper planMapper;

    @Lazy
    @Autowired
    private ClientMapper clientMapper;

    @Lazy
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Lazy
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public MainHQDTO toDTO(MainHQEntity entity) {

        if (entity == null)
            return null;

        return MainHQDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .plans(entity.getPlanEntities()
                        .stream()
                        .map(planMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .invoices(entity.getInvoiceEntities()
                        .stream()
                        .map(invoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .clients(entity.getClientEntities()
                        .stream()
                        .map(clientMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .employees(entity.getEmployeeEntities()
                        .stream()
                        .map(employeeMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public MainHQMiniDTO toSmallDTO(MainHQEntity entity) {

        if (entity == null)
            return null;

        return MainHQMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public MainHQEntity toEntity(MainHQForm form) {
        if (form == null)
            return null;

        return MainHQEntity.builder()
                .name(form.getName())
                .build();
    }
}

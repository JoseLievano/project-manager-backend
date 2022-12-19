package com.bgsystem.bugtracker.models.HQ.employee;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmployeePredicate extends CommonPathExpression<EmployeeEntity> {

    private final QEmployeeEntity employeeEntity = QEmployeeEntity.employeeEntity;

    public EmployeePredicate(){
        super();
        this.entityPath = new PathBuilder<EmployeeEntity>(EmployeeEntity.class, "employeeEntity");
    }

}

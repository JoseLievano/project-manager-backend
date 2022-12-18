package com.bgsystem.bugtracker.models.client.bsEmployee;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsEmployeePredicate extends CommonPathExpression<bsEmployeeEntity> {

    private final QbsEmployeeEntity bsEmployeeEntity = QbsEmployeeEntity.bsEmployeeEntity;

    public bsEmployeePredicate(){
        super( );
        this.entityPath = new PathBuilder<bsEmployeeEntity>(bsEmployeeEntity.class, "bsEmployeeEntity");
    }
}

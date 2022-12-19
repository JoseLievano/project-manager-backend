package com.bgsystem.bugtracker.models.HQ.admin;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class AdminPredicate extends CommonPathExpression<AdminEntity> {

    private final QAdminEntity adminEntity = QAdminEntity.adminEntity;

    public AdminPredicate(){
        super( );
        this.entityPath = new PathBuilder<AdminEntity>(AdminEntity.class, "adminEntity");
    }

}

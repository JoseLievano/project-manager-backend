package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsTypePredicate extends CommonPathExpression<bsTypeEntity> {

    private final QbsTypeEntity bsTypeEntity = QbsTypeEntity.bsTypeEntity;

    public bsTypePredicate(){
        super( );
        this.entityPath = new PathBuilder<bsTypeEntity>(bsTypeEntity.class, "bsTypeEntity");
    }

}

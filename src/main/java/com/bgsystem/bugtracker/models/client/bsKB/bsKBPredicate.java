package com.bgsystem.bugtracker.models.client.bsKB;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsKBPredicate extends CommonPathExpression<bsKBEntity> {

    private final QbsKBEntity bsKBEntity = QbsKBEntity.bsKBEntity;

    public bsKBPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsKBEntity>(bsKBEntity.class, "bsKBEntity");
    }

}

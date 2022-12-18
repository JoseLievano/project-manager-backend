package com.bgsystem.bugtracker.models.client.bsClient;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsClientPredicate extends CommonPathExpression<bsClientEntity> {

    private final QbsClientEntity bsClientEntity = QbsClientEntity.bsClientEntity;

    public bsClientPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsClientEntity>(bsClientEntity.class, "bsClientEntity");
    }

}

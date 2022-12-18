package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsDocPredicate extends CommonPathExpression<bsDocEntity> {

    private final QbsDocEntity bsDocEntity = QbsDocEntity.bsDocEntity;

    public bsDocPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsDocEntity>(bsDocEntity.class, "bsDocEntity");
    }

}

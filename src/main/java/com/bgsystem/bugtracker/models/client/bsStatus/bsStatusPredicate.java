package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsStatusPredicate extends CommonPathExpression<bsStatusEntity> {

    private final QbsStatusEntity bsStatusEntity = QbsStatusEntity.bsStatusEntity;

    public bsStatusPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsStatusEntity>(bsStatusEntity.class, "bsStatusEntity");
    }

}

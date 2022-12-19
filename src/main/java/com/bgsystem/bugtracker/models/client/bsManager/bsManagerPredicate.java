package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsManagerPredicate extends CommonPathExpression<bsManagerEntity> {

    private final QbsManagerEntity bsManagerEntity = QbsManagerEntity.bsManagerEntity;

    public bsManagerPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsManagerEntity>(bsManagerEntity.class, "bsManagerEntity");
    }

}

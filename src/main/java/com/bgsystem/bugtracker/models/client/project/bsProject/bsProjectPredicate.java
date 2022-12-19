package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsProjectPredicate extends CommonPathExpression<bsProjectEntity> {

    private final QbsProjectEntity bsProjectEntity = QbsProjectEntity.bsProjectEntity;

    public bsProjectPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsProjectEntity>(bsProjectEntity.class, "bsProjectEntity");
    }

}

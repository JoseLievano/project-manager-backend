package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsTaskCategoryPredicate extends CommonPathExpression<bsTaskCategoryEntity> {

    private final QbsTaskCategoryEntity bsTaskCategoryEntity = QbsTaskCategoryEntity.bsTaskCategoryEntity;

    public bsTaskCategoryPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsTaskCategoryEntity>(bsTaskCategoryEntity.class, "bsTaskCategoryEntity");
    }
}

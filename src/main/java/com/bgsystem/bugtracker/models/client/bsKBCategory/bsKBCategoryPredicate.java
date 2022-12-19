package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsKBCategoryPredicate extends CommonPathExpression<bsKBCategoryEntity> {

    private final QbsKBCategoryEntity bsKBCategoryEntity = QbsKBCategoryEntity.bsKBCategoryEntity;

    public bsKBCategoryPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsKBCategoryEntity>(bsKBCategoryEntity.class, "bsKBCategoryEntity");
    }

}

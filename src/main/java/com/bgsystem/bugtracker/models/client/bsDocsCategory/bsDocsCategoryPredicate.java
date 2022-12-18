package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsDocsCategoryPredicate extends CommonPathExpression<bsDocsCategoryEntity> {

    private final QbsDocsCategoryEntity bsDocsCategoryEntity = QbsDocsCategoryEntity.bsDocsCategoryEntity;

    public bsDocsCategoryPredicate(){
        super();
        this.entityPath = new PathBuilder<bsDocsCategoryEntity>(bsDocsCategoryEntity.class, "bsDocsCategoryEntity");
    }

}

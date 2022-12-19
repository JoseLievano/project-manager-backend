package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrKBCategoryPredicate extends CommonPathExpression<bsPrKBCategoryEntity> {

    private final QbsPrKBCategoryEntity bsPrKBCategoryEntity = QbsPrKBCategoryEntity.bsPrKBCategoryEntity;

    public bsPrKBCategoryPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsPrKBCategoryEntity>(bsPrKBCategoryEntity.class, "bsPrKBCategoryEntity");
    }

}

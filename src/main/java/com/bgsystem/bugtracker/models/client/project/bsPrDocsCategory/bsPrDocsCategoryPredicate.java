package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrDocsCategoryPredicate extends CommonPathExpression<bsPrDocsCategoryEntity> {

    private final QbsPrDocsCategoryEntity bsPrDocsCategoryEntity = QbsPrDocsCategoryEntity.bsPrDocsCategoryEntity;

    public bsPrDocsCategoryPredicate(){
        super();
        this.entityPath = new PathBuilder<bsPrDocsCategoryEntity>(bsPrDocsCategoryEntity.class, "bsPrDocsCategoryEntity");
    }

}

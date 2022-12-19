package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrKBPredicate extends CommonPathExpression<bsPrKBEntity> {

    private final QbsPrKBEntity bsPrKBEntity = QbsPrKBEntity.bsPrKBEntity;

    public bsPrKBPredicate(){
        super();
        this.entityPath = new PathBuilder<bsPrKBEntity>(bsPrKBEntity.class, "bsPrKBEntity");
    }

}

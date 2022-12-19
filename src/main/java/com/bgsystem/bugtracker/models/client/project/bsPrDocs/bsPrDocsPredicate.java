package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrDocsPredicate extends CommonPathExpression<bsPrDocsEntity> {

    private final QbsPrDocsEntity bsPrDocsEntity = QbsPrDocsEntity.bsPrDocsEntity;

    public bsPrDocsPredicate(){
        super( );
         this.entityPath = new PathBuilder<bsPrDocsEntity>(bsPrDocsEntity.class, "bsPrDocsEntity");
    }

}

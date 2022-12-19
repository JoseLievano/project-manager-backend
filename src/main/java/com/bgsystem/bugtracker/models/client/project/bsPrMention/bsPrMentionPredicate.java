package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrMentionPredicate extends CommonPathExpression<bsPrMentionEntity> {

    private final QbsPrMentionEntity bsPrMentionEntity = QbsPrMentionEntity.bsPrMentionEntity;

    public bsPrMentionPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsPrMentionEntity>(bsPrMentionEntity.class, "bsPrMentionEntity");
    }

}

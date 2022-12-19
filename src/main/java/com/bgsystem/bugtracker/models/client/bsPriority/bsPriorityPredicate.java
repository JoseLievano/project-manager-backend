package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPriorityPredicate extends CommonPathExpression<bsPriorityEntity> {

    private final QbsPriorityEntity bsPriorityEntity = QbsPriorityEntity.bsPriorityEntity;

    public bsPriorityPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsPriorityEntity>(bsPriorityEntity.class, "bsPriorityEntity");
    }
}

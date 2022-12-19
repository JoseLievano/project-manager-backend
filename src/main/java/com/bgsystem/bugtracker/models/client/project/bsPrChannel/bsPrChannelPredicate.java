package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrChannelPredicate extends CommonPathExpression<bsPrChannelEntity> {

    private final QbsPrChannelEntity bsPrChannelEntity = QbsPrChannelEntity.bsPrChannelEntity;

    public bsPrChannelPredicate(){

        super();
        this.entityPath = new PathBuilder<bsPrChannelEntity>(bsPrChannelEntity.class, "bsPrChannelEntity");

    }

}

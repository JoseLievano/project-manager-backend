package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientPredicate extends CommonPathExpression<ClientEntity> {

    private final QClientEntity clientEntity = QClientEntity.clientEntity;

    public ClientPredicate(){
        super( );
        this.entityPath = new PathBuilder<ClientEntity>(ClientEntity.class, "clientEntity");
    }

}

package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrTaskPredicate extends CommonPathExpression<bsPrTaskEntity> {

    private final QbsPrTaskEntity bsPrTaskEntity = QbsPrTaskEntity.bsPrTaskEntity;

    public bsPrTaskPredicate() {
        super();
        this.entityPath = new PathBuilder<bsPrTaskEntity>(bsPrTaskEntity.class, "bsPrTaskEntity");
    }

}

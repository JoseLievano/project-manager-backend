package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class PlanPredicate extends CommonPathExpression<PlanEntity> {

    private final QPlanEntity planEntity = QPlanEntity.planEntity;

    public PlanPredicate(){
        super();
        this.entityPath = new PathBuilder<PlanEntity>(PlanEntity.class, "planEntity");
    }

}

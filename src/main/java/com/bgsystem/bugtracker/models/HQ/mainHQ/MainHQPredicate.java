package com.bgsystem.bugtracker.models.HQ.mainHQ;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class MainHQPredicate extends CommonPathExpression<MainHQEntity> {

    private final QMainHQEntity qMainHQEntity = QMainHQEntity.mainHQEntity;

    public MainHQPredicate() {
        super();
        this.entityPath = new PathBuilder<MainHQEntity>(MainHQEntity.class, "mainHQEntity");
    }
}

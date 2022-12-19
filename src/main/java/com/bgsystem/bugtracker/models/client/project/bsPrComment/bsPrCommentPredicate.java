package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsPrCommentPredicate extends CommonPathExpression<bsPrCommentEntity> {

    private final QbsPrCommentEntity bsPrCommentEntity = QbsPrCommentEntity.bsPrCommentEntity;

    public bsPrCommentPredicate (){
        super();
        this.entityPath = new PathBuilder<bsPrCommentEntity>(bsPrCommentEntity.class, "bsPrCommentEntity");
    }

}

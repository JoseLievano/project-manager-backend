package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrCommentPredicate extends CommonPathExpression<bsPrCommentEntity> {

    private final QbsPrCommentEntity bsPrCommentEntity = QbsPrCommentEntity.bsPrCommentEntity;

    public bsPrCommentPredicate (){
        super();
        this.entityPath = new PathBuilder<bsPrCommentEntity>(bsPrCommentEntity.class, "bsPrCommentEntity");
        this.entityFields.add("channel");
        this.entityFields.add("mention");
        this.entityFields.add("project");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()){
            case "channel" -> getChannelExpression(filter);
            case "mention" -> getMentionExpression(filter);
            case "project" -> getProjectExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };
    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrCommentEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrCommentEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsPrCommentEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsPrCommentEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDate = entityPath.getDate(bsPrCommentEntity.project.dueDate.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(dueDate, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return projectExpression;

    }

    private BooleanExpression getMentionExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression mentionExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrCommentEntity.mentions.any().id;
                    mentionExpression = addOrExpression(mentionExpression, getNumberPathBooleanExpression(idPath, operation));
                }
            }
        }

        return mentionExpression;

    }

    private BooleanExpression getChannelExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression channelExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrCommentEntity.channel.id;
                    channelExpression = addOrExpression(channelExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrCommentEntity.channel.name;
                    channelExpression = addOrExpression(channelExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isPublic" -> {
                    BooleanPath isPublicPath = bsPrCommentEntity.channel.isPublic;
                    channelExpression = addOrExpression(channelExpression, getBooleanPathBooleanExpression(isPublicPath, operation));
                }
                case "creationDate" -> {
                    DatePath<Date> creationDatePath = entityPath.getDate(bsPrCommentEntity.channel.creationDate.toString(), java.util.Date.class);
                    channelExpression = addOrExpression(channelExpression, getDatePathBooleanExpression(creationDatePath, operation));
                }
                case "description" -> {
                    StringPath descriptionPath = bsPrCommentEntity.channel.description;
                    channelExpression = addOrExpression(channelExpression, getStringPathBooleanExpression(descriptionPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return channelExpression;

    }
}

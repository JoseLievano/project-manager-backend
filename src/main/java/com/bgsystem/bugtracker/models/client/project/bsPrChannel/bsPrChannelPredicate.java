package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrChannelPredicate extends CommonPathExpression<bsPrChannelEntity> {

    private final QbsPrChannelEntity bsPrChannelEntity = QbsPrChannelEntity.bsPrChannelEntity;
    private final com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelRepository bsPrChannelRepository;

    public bsPrChannelPredicate(bsPrChannelRepository bsPrChannelRepository){

        super();
        this.entityPath = new PathBuilder<bsPrChannelEntity>(bsPrChannelEntity.class, "bsPrChannelEntity");
        this.entityFields.add("member");
        this.entityFields.add("comment");
        this.entityFields.add("project");
        this.entityFields.add("author");
        this.bsPrChannelRepository = bsPrChannelRepository;
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "member" -> getMemberExpression(filter);
            case "comment" -> getCommentExpression(filter);
            case "project" -> getProjectExpression(filter);
            case "author" -> getAuthorExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getAuthorExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression authorExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrChannelEntity.author.id;
                    authorExpression = addOrExpression(authorExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "firstName" -> {
                    StringPath firstNamePath = bsPrChannelEntity.author.firstName;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(firstNamePath, operation));
                }
                case "lastName" -> {
                    StringPath lastNamePath = bsPrChannelEntity.author.lastName;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(lastNamePath, operation));
                }
                case "email" -> {
                    StringPath emailPath = bsPrChannelEntity.author.email;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(emailPath, operation));
                }
                case "username" -> {
                    StringPath usernamePath = bsPrChannelEntity.author.username;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(usernamePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return authorExpression;

    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrChannelEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrChannelEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsPrChannelEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsPrChannelEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDatePath = entityPath.getDate(bsPrChannelEntity.project.dueDate.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(dueDatePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return projectExpression;

    }

    private BooleanExpression getCommentExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression commentExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrChannelEntity.comments.any().id;
                    commentExpression = addOrExpression(commentExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "commentContent" -> {
                    StringPath commentContentPath = bsPrChannelEntity.comments.any().commentContent;
                    commentExpression = addOrExpression(commentExpression, getStringPathBooleanExpression(commentContentPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return commentExpression;

    }

    private BooleanExpression getMemberExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression memberExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrChannelEntity.members.any().id;
                    memberExpression = addOrExpression(memberExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "firstName" -> {
                    StringPath firstNamePath = bsPrChannelEntity.members.any().firstName;
                    memberExpression = addOrExpression(memberExpression, getStringPathBooleanExpression(firstNamePath, operation));
                }
                case "lastName" -> {
                    StringPath lastNamePath = bsPrChannelEntity.members.any().lastName;
                    memberExpression = addOrExpression(memberExpression, getStringPathBooleanExpression(lastNamePath, operation));
                }
                case "email" -> {
                    StringPath emailPath = bsPrChannelEntity.members.any().email;
                    memberExpression = addOrExpression(memberExpression, getStringPathBooleanExpression(emailPath, operation));
                }
                case "userName" -> {
                    StringPath userNamePath = bsPrChannelEntity.members.any().username;
                    memberExpression = addOrExpression(memberExpression, getStringPathBooleanExpression(userNamePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
            }
        }

        return memberExpression;

    }
}

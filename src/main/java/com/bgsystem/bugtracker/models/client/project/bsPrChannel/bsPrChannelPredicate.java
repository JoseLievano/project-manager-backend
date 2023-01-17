package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.stereotype.Service;

@Service
public class bsPrChannelPredicate extends CommonPathExpression<bsPrChannelEntity> {

    private final QbsPrChannelEntity bsPrChannelEntity = QbsPrChannelEntity.bsPrChannelEntity;

    public bsPrChannelPredicate(){

        super();
        this.entityPath = new PathBuilder<bsPrChannelEntity>(bsPrChannelEntity.class, "bsPrChannelEntity");
        this.entityFields.add("member");
        this.entityFields.add("comment");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "member" -> getMemberExpression(filter);
            case "comment" -> getCommentExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

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

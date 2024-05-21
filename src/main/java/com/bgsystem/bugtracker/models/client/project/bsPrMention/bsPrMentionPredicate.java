package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrMentionPredicate extends CommonPathExpression<bsPrMentionEntity> {

    private final QbsPrMentionEntity bsPrMentionEntity = QbsPrMentionEntity.bsPrMentionEntity;

    public bsPrMentionPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsPrMentionEntity>(bsPrMentionEntity.class, "bsPrMentionEntity");
        this.entityFields.add("comment");
        this.entityFields.add("author");
        this.entityFields.add("mentionedUser");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "comment" -> getCommentExpression(filter);
            case "author" -> getAuthorExpression(filter);
            case "mentionedUser" -> getMentionedUserExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getMentionedUserExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression mentionedUserExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrMentionEntity.mentionedUser.id;
                    mentionedUserExpression = addOrExpression(mentionedUserExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "firstName" -> {
                    StringPath firstNamePath = bsPrMentionEntity.mentionedUser.firstName;
                    mentionedUserExpression = addOrExpression(mentionedUserExpression, getStringPathBooleanExpression(firstNamePath, operation));
                }
                case "lastName" -> {
                    StringPath lastNamePath = bsPrMentionEntity.mentionedUser.lastName;
                    mentionedUserExpression = addOrExpression(mentionedUserExpression, getStringPathBooleanExpression(lastNamePath, operation));
                }
                case "email" -> {
                    StringPath emailPath = bsPrMentionEntity.mentionedUser.email;
                    mentionedUserExpression = addOrExpression(mentionedUserExpression, getStringPathBooleanExpression(emailPath, operation));
                }
                case "username" -> {
                    StringPath usernamePath = bsPrMentionEntity.mentionedUser.username;
                    mentionedUserExpression = addOrExpression(mentionedUserExpression, getStringPathBooleanExpression(usernamePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return mentionedUserExpression;

    }

    private BooleanExpression getAuthorExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression authorExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrMentionEntity.author.id;
                    authorExpression = addOrExpression(authorExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "firstName" -> {
                    StringPath firstNamePath = bsPrMentionEntity.author.firstName;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(firstNamePath, operation));
                }
                case "lastName" -> {
                    StringPath lastNamePath = bsPrMentionEntity.author.lastName;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(lastNamePath, operation));
                }
                case "email" -> {
                    StringPath emailPath = bsPrMentionEntity.author.email;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(emailPath, operation));
                }
                case "username" -> {
                    StringPath usernamePath = bsPrMentionEntity.author.username;
                    authorExpression = addOrExpression(authorExpression, getStringPathBooleanExpression(usernamePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return authorExpression;

    }

    private BooleanExpression getCommentExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression commentExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrMentionEntity.comment.id;
                    commentExpression = addOrExpression(commentExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "commentContent" -> {
                    StringPath commentContentPath = bsPrMentionEntity.comment.commentContent;
                    commentExpression = addOrExpression(commentExpression, getStringPathBooleanExpression(commentContentPath, operation));
                }
                case "commentDate" -> {
                    DatePath<Date> commentDatePath = entityPath.getDate(bsPrMentionEntity.comment.commentDate.toString(), java.util.Date.class);
                    commentExpression = addOrExpression(commentExpression, getDatePathBooleanExpression(commentDatePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return commentExpression;

    }
}

package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrDocsCategoryPredicate extends CommonPathExpression<bsPrDocsCategoryEntity> {

    private final QbsPrDocsCategoryEntity bsPrDocsCategoryEntity = QbsPrDocsCategoryEntity.bsPrDocsCategoryEntity;

    public bsPrDocsCategoryPredicate(){
        super();
        this.entityPath = new PathBuilder<bsPrDocsCategoryEntity>(bsPrDocsCategoryEntity.class, "bsPrDocsCategoryEntity");
        this.entityFields.add("project");
        this.entityFields.add("doc");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "project" -> getProjectExpression(filter);
            case "doc" -> getDocExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getDocExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression docExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrDocsCategoryEntity.docs.any().id;
                    docExpression = addOrExpression(docExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "title" -> {
                    StringPath titlePath = bsPrDocsCategoryEntity.docs.any().title;
                    docExpression = addOrExpression(docExpression, getStringPathBooleanExpression(titlePath, operation));
                }
                case "content" -> {
                    StringPath contentPath = bsPrDocsCategoryEntity.docs.any().content;
                    docExpression = addOrExpression(docExpression, getStringPathBooleanExpression(contentPath,operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return docExpression;

    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrDocsCategoryEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrDocsCategoryEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsPrDocsCategoryEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsPrDocsCategoryEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath,operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDate = entityPath.getDate(bsPrDocsCategoryEntity.project.dueDate.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(dueDate, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return projectExpression;

    }
}

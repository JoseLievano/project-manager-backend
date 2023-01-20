package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrKBCategoryPredicate extends CommonPathExpression<bsPrKBCategoryEntity> {

    private final QbsPrKBCategoryEntity bsPrKBCategoryEntity = QbsPrKBCategoryEntity.bsPrKBCategoryEntity;

    public bsPrKBCategoryPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsPrKBCategoryEntity>(bsPrKBCategoryEntity.class, "bsPrKBCategoryEntity");
        this.entityFields.add("project");
        this.entityFields.add("kbs");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "project" -> getProjectExpression(filter);
            case "kbs" -> getKBExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getKBExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression kbExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrKBCategoryEntity.kbs.any().id;
                    kbExpression = addOrExpression(kbExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "title" -> {
                    StringPath titlePath = bsPrKBCategoryEntity.kbs.any().title;
                    kbExpression = addOrExpression(kbExpression, getStringPathBooleanExpression(titlePath, operation));
                }
                case "content" -> {
                    StringPath contentPath = bsPrKBCategoryEntity.kbs.any().content;
                    kbExpression = addOrExpression(kbExpression, getStringPathBooleanExpression(contentPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return kbExpression;

    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrKBCategoryEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrKBCategoryEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsPrKBCategoryEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsPrKBCategoryEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDate = entityPath.getDate(bsPrKBCategoryEntity.project.dueDate.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(dueDate, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return projectExpression;

    }
}

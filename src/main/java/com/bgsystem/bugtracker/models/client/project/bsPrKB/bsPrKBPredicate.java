package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrKBPredicate extends CommonPathExpression<bsPrKBEntity> {

    private final QbsPrKBEntity bsPrKBEntity = QbsPrKBEntity.bsPrKBEntity;

    public bsPrKBPredicate(){
        super();
        this.entityPath = new PathBuilder<bsPrKBEntity>(bsPrKBEntity.class, "bsPrKBEntity");
        this.entityFields.add("project");
        this.entityFields.add("category");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()){
            case "project" -> getProjectExpression(filter);
            case "category" -> getCategoryExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };
    }

    private BooleanExpression getCategoryExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression categoryExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrKBEntity.category.id;
                    categoryExpression = addOrExpression(categoryExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrKBEntity.category.name;
                    categoryExpression = addOrExpression(categoryExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return categoryExpression;

    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrKBEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrKBEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsPrKBEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsPrKBEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDatePath = entityPath.getDate(bsPrKBEntity.project.dueDate.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(dueDatePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return projectExpression;

    }
}

package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrDocsPredicate extends CommonPathExpression<bsPrDocsEntity> {

    private final QbsPrDocsEntity bsPrDocsEntity = QbsPrDocsEntity.bsPrDocsEntity;

    public bsPrDocsPredicate(){
        super( );
         this.entityPath = new PathBuilder<bsPrDocsEntity>(bsPrDocsEntity.class, "bsPrDocsEntity");
         this.entityFields.add("category");
         this.entityFields.add("project");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()){
            case "category" -> getCategoryExpression(filter);
            case "project" -> getProjectExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };
    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrDocsEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrDocsEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsPrDocsEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsPrDocsEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDatePath = entityPath.getDate(bsPrDocsEntity.project.dueDate.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(dueDatePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return projectExpression;

    }

    private BooleanExpression getCategoryExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression categoryExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrDocsEntity.category.id;
                    categoryExpression = addOrExpression(categoryExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrDocsEntity.category.name;
                    categoryExpression = addOrExpression(categoryExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return categoryExpression;

    }
}

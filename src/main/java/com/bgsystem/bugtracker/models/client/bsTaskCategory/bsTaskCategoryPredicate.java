package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsTaskCategoryPredicate extends CommonPathExpression<bsTaskCategoryEntity> {

    private final QbsTaskCategoryEntity bsTaskCategoryEntity = QbsTaskCategoryEntity.bsTaskCategoryEntity;

    public bsTaskCategoryPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsTaskCategoryEntity>(bsTaskCategoryEntity.class, "bsTaskCategoryEntity");
        this.entityFields.add("task");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "task" -> getTaskExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getTaskExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression taskExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsTaskCategoryEntity.tasks.any().id;
                    taskExpression = addOrExpression(taskExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" ->{
                    StringPath namePath = bsTaskCategoryEntity.tasks.any().name;
                    taskExpression = addOrExpression(taskExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isInternal" -> {
                    BooleanPath isInternalPath = bsTaskCategoryEntity.tasks.any().isInternal;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isInternalPath, operation));
                }
                case "isOverDue" ->{
                    BooleanPath isOverDuePath = bsTaskCategoryEntity.tasks.any().isOverDue;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "isDone" ->{
                    BooleanPath isDone = bsTaskCategoryEntity.tasks.any().isDone;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isDone, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }

        }

        return taskExpression;
    }
}

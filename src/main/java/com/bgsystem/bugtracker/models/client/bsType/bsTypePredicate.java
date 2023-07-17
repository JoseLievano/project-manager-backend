package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsTypePredicate extends CommonPathExpression<bsTypeEntity> {

    private final QbsTypeEntity bsTypeEntity = QbsTypeEntity.bsTypeEntity;

    public bsTypePredicate(){
        super( );
        this.entityPath = new PathBuilder<bsTypeEntity>(bsTypeEntity.class, "bsTypeEntity");
        this.entityFields.add("tasks");
        this.entityFields.add("taskCategory");
        this.entityFields.add("business");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "task" -> getTaskExpression(filter);
            case "taskCategory" -> getTaskCategoryExpression(filter);
            case "business" -> getBusinessExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getTaskCategoryExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression taskCategoryExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsTypeEntity.taskCategories.any().id;
                    taskCategoryExpression = addOrExpression(taskCategoryExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsTypeEntity.taskCategories.any().name;
                    taskCategoryExpression = addOrExpression(taskCategoryExpression, getStringPathBooleanExpression(namePath, operation));
                }
            }

        }

        return taskCategoryExpression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsTypeEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsTypeEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsTypeEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoicePath = bsTypeEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsTypeEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsTypeEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return businessExpression;

    }

    private BooleanExpression getTaskExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression taskExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" ->{
                    NumberPath<Long> idPath = bsTypeEntity.tasks.any().id;
                    taskExpression = addOrExpression(taskExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" ->{
                    StringPath namePath = bsTypeEntity.tasks.any().name;
                    taskExpression = addOrExpression(taskExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isInternal" ->{
                    BooleanPath isInternalPath = bsTypeEntity.tasks.any().isInternal;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isInternalPath, operation));
                }
                case "isOverDue" -> {
                    BooleanPath isOverDuePath = bsTypeEntity.tasks.any().isOverDue;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "isDone" -> {
                    BooleanPath isDonePath = bsTypeEntity.tasks.any().isDone;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isDonePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }

        }

        return taskExpression;

    }
}

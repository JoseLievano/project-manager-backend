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
        this.entityFields.add("tasks");
        this.entityFields.add("business");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "tasks" -> getTaskExpression(filter);
            case "business" -> getBusinessExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsTaskCategoryEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsTaskCategoryEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsTaskCategoryEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoicePath = bsTaskCategoryEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsTaskCategoryEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsTaskCategoryEntity.business.isActive;
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

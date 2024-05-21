package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsStatusPredicate extends CommonPathExpression<bsStatusEntity> {

    private final QbsStatusEntity bsStatusEntity = QbsStatusEntity.bsStatusEntity;

    public bsStatusPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsStatusEntity>(bsStatusEntity.class, "bsStatusEntity");
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
                    NumberPath<Long> idPath = bsStatusEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsStatusEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsStatusEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoicePath = bsStatusEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsStatusEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsStatusEntity.business.isActive;
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
                    NumberPath<Long> idPath = bsStatusEntity.tasks.any().id;
                    taskExpression = addOrExpression(taskExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsStatusEntity.tasks.any().name;
                    taskExpression = addOrExpression(taskExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isInternal" ->{
                    BooleanPath isInternalPath = bsStatusEntity.tasks.any().isInternal;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isInternalPath, operation));
                }
                case "isOverDue" -> {
                    BooleanPath isOverDuePath = bsStatusEntity.tasks.any().isOverDue;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "isDone" ->{
                    BooleanPath isDonePath = bsStatusEntity.tasks.any().isDone;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isDonePath, operation));
                }
            }

        }

        return taskExpression;

    }
}

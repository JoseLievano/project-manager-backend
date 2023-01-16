package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPriorityPredicate extends CommonPathExpression<bsPriorityEntity> {

    private final QbsPriorityEntity bsPriorityEntity = QbsPriorityEntity.bsPriorityEntity;

    public bsPriorityPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsPriorityEntity>(bsPriorityEntity.class, "bsPriorityEntity");
        this.entityFields.add("business");
        this.entityFields.add("tasks");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()) {
            case "business" -> getBusinessExpression(filter);
            case "tasks" -> getTasksExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };
    }

    private BooleanExpression getTasksExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression tasksExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPriorityEntity.tasks.any().id;
                    tasksExpression = addOrExpression(tasksExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPriorityEntity.tasks.any().name;
                    tasksExpression = addOrExpression(tasksExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isInternal" -> {
                    BooleanPath isInternalPath = bsPriorityEntity.tasks.any().isInternal;
                    tasksExpression = addOrExpression(tasksExpression, getBooleanPathBooleanExpression(isInternalPath, operation));
                }
                case "isOverDue" -> {
                    BooleanPath isOverDuePath = bsPriorityEntity.tasks.any().isOverDue;
                    tasksExpression = addOrExpression(tasksExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "isDone" -> {
                    BooleanPath isDonePath = bsPriorityEntity.tasks.any().isDone;
                    tasksExpression = addOrExpression(tasksExpression, getBooleanPathBooleanExpression(isDonePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }

        return tasksExpression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPriorityEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPriorityEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsPriorityEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" ->{
                    BooleanPath pendingInvoicePath = bsPriorityEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsPriorityEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsPriorityEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }

        return businessExpression;

    }
}

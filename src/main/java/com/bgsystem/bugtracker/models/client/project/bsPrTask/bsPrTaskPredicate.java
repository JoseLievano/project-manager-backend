package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrTaskPredicate extends CommonPathExpression<bsPrTaskEntity> {

    private final QbsPrTaskEntity bsPrTaskEntity = QbsPrTaskEntity.bsPrTaskEntity;

    public bsPrTaskPredicate() {
        super();
        this.entityPath = new PathBuilder<bsPrTaskEntity>(bsPrTaskEntity.class, "bsPrTaskEntity");
        this.entityFields.add("business");
        this.entityFields.add("category");
        this.entityFields.add("project");
        this.entityFields.add("type");
        this.entityFields.add("priority");
        this.entityFields.add("status");
        this.entityFields.add("invoice");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()){
            case "business" -> getBusinessExpression(filter);
            case "category" -> getCategoryExpression(filter);
            case "project" -> getProjectExpression(filter);
            case "type" -> getTypeExpression(filter);
            case "priority" -> getPriorityExpression(filter);
            case "status" -> getStatusExpression(filter);
            case "invoice" -> getInvoiceExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };
    }

    private BooleanExpression getInvoiceExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression invoiceExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrTaskEntity.invoice.id;
                    invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "dateGenerated" -> {
                    DatePath<Date> dateGeneratedPath = entityPath.getDate(bsPrTaskEntity.invoice.dateGenerated.toString(), java.util.Date.class);
                    invoiceExpression = addOrExpression(invoiceExpression, getDatePathBooleanExpression(dateGeneratedPath, operation));
                }
                case "limitDate" -> {
                    DatePath<Date> limitDatePath = entityPath.getDate(bsPrTaskEntity.invoice.limitDate.toString(), java.util.Date.class);
                    invoiceExpression = addOrExpression(invoiceExpression, getDatePathBooleanExpression(limitDatePath, operation));
                }
                case "amount" -> {
                    NumberPath<Double> amountPath = bsPrTaskEntity.invoice.amount;
                    invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(amountPath, operation));
                }
                case "isPaid" -> {
                    BooleanPath isPaidPath = bsPrTaskEntity.invoice.isPaid;
                    invoiceExpression = addOrExpression(invoiceExpression, getBooleanPathBooleanExpression(isPaidPath, operation));
                }
                case "isOverDue" -> {
                    BooleanPath isOverDuePath = bsPrTaskEntity.invoice.isOverDue;
                    invoiceExpression = addOrExpression(invoiceExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "number" -> {
                    StringPath numberPath = bsPrTaskEntity.invoice.number;
                    invoiceExpression = addOrExpression(invoiceExpression, getStringPathBooleanExpression(numberPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return invoiceExpression;

    }

    private BooleanExpression getStatusExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression statusExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrTaskEntity.status.id;
                    statusExpression = addOrExpression(statusExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrTaskEntity.status.name;
                    statusExpression = addOrExpression(statusExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "color" -> {
                    StringPath colorPath = bsPrTaskEntity.status.color;
                    statusExpression = addOrExpression(statusExpression, getStringPathBooleanExpression(colorPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return statusExpression;

    }

    private BooleanExpression getPriorityExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression priorityExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrTaskEntity.priority.id;
                    priorityExpression = addOrExpression(priorityExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrTaskEntity.priority.name;
                    priorityExpression = addOrExpression(priorityExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "priorityOrder" -> {
                    NumberPath<Integer> priorityOrderPath = bsPrTaskEntity.priority.priorityOrder;
                    priorityExpression = addOrExpression(priorityExpression, getNumberPathBooleanExpression(priorityOrderPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return priorityExpression;

    }

    private BooleanExpression getTypeExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression typeExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrTaskEntity.type.id;
                    typeExpression = addOrExpression(typeExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrTaskEntity.type.name;
                    typeExpression = addOrExpression(typeExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return typeExpression;

    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrTaskEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrTaskEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsPrTaskEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsPrTaskEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDatePath = entityPath.getDate(bsPrTaskEntity.project.dueDate.toString(), java.util.Date.class);
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
                    NumberPath<Long> idPath = bsPrTaskEntity.category.id;
                    categoryExpression = addOrExpression(categoryExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrTaskEntity.category.name;
                    categoryExpression = addOrExpression(categoryExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return categoryExpression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsPrTaskEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsPrTaskEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsPrTaskEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "dateCreated" -> {
                    DatePath<Date> dateCreatedPath = entityPath.getDate(bsPrTaskEntity.business.dateCreated.toString(), java.util.Date.class);
                    businessExpression = addOrExpression(businessExpression, getDatePathBooleanExpression(dateCreatedPath, operation));
                }
                case "pendingInvoice" ->{
                    BooleanPath pendingInvoicePath = bsPrTaskEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsPrTaskEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsPrTaskEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return businessExpression;

    }
}

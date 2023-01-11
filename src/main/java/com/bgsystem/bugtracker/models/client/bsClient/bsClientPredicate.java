package com.bgsystem.bugtracker.models.client.bsClient;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsClientPredicate extends CommonPathExpression<bsClientEntity> {

    private final QbsClientEntity bsClientEntity = QbsClientEntity.bsClientEntity;

    public bsClientPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsClientEntity>(bsClientEntity.class, "bsClientEntity");
        this.entityFields.add("projects");
        this.entityFields.add("invoices");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()) {
            case "projects" -> getProjectsExpression(filter);
            case "invoices" -> getInvoicesExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };
    }


    private BooleanExpression getInvoicesExpression(FilterRequest filter) throws BadOperator {
        BooleanExpression invoiceExpression = null;
        for (FilterOperator operation : filter.getOperations()) {
            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> numberPath = bsClientEntity.invoices.any().id;
                    invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(numberPath, operation));
                }
                case "number" -> {
                    StringPath stringPath = bsClientEntity.invoices.any().number;
                    invoiceExpression = addOrExpression(invoiceExpression, getStringPathBooleanExpression(stringPath, operation));
                }
                case "amount" -> {
                    NumberPath<Double> numberPath = bsClientEntity.invoices.any().amount;
                    invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(numberPath, operation));
                }
                case "isPaid" -> {
                    BooleanPath booleanPath = bsClientEntity.invoices.any().isPaid;
                    invoiceExpression = addOrExpression(invoiceExpression, getBooleanPathBooleanExpression(booleanPath, operation));
                }
                case "isOverdue" -> {
                    BooleanPath booleanOverDuePath = bsClientEntity.invoices.any().isOverDue;
                    invoiceExpression = addOrExpression(invoiceExpression, getBooleanPathBooleanExpression(booleanOverDuePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }
        return invoiceExpression;
    }

    private BooleanExpression getProjectsExpression(FilterRequest filter) throws BadOperator {
        BooleanExpression projectsExpression = null;
        for (FilterOperator operation : filter.getOperations()) {
            switch (operation.getField()) {
                case "id":
                    NumberPath<Long> numberPath = bsClientEntity.projects.any().id;
                    projectsExpression = addOrExpression(projectsExpression, getNumberPathBooleanExpression(numberPath, operation));
                    break;
                case "name":
                    StringPath stringPath = bsClientEntity.projects.any().name;
                    projectsExpression = addOrExpression(projectsExpression, getStringPathBooleanExpression(stringPath, operation));
                    break;
                case "isCompleted":
                    BooleanPath booleanPath = bsClientEntity.projects.any().isCompleted;
                    projectsExpression = addOrExpression(projectsExpression, getBooleanPathBooleanExpression(booleanPath, operation));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }
        return projectsExpression;
    }


}

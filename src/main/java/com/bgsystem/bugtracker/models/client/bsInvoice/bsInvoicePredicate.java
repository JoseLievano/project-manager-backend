package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsInvoicePredicate extends CommonPathExpression<bsInvoiceEntity> {

    private final QbsInvoiceEntity bsInvoiceEntity = QbsInvoiceEntity.bsInvoiceEntity;
    private final bsInvoiceRepository bsInvoiceRepository;

    public bsInvoicePredicate(bsInvoiceRepository bsInvoiceRepository){
        super( );
        this.entityPath = new PathBuilder<bsInvoiceEntity>(bsInvoiceEntity.class, "bsInvoiceEntity");
        this.entityFields.add("client");
        this.entityFields.add("project");
        this.entityFields.add("business");
        this.entityFields.add("task");

        this.bsInvoiceRepository = bsInvoiceRepository;
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()) {
            case "client" -> getClientExpression(filter);
            case "project" -> getProjectExpression(filter);
            case "business" -> getBusinessExpression(filter);
            case "task" -> getTaskExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };

    }

    private BooleanExpression getTaskExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression taskExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsInvoiceEntity.task.id;
                    taskExpression = addOrExpression(taskExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsInvoiceEntity.task.name;
                    taskExpression = addOrExpression(taskExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDatePath = entityPath.getDate(bsInvoiceEntity.task.dueDate.toString(), java.util.Date.class);
                    taskExpression = addOrExpression(taskExpression, getDatePathBooleanExpression(dueDatePath, operation));
                }
                case "isInternal" -> {
                    BooleanPath isInternalPath = bsInvoiceEntity.task.isInternal;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isInternalPath, operation));
                }
                case "isOverDue" -> {
                    BooleanPath isOverDuePath = bsInvoiceEntity.task.isOverDue;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "isDone" -> {
                    BooleanPath isDonePath = bsInvoiceEntity.task.isDone;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isDonePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }

        }

        return taskExpression;
    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator{

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsInvoiceEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsInvoiceEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsInvoiceEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoicePath = bsInvoiceEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsInvoiceEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsInvoiceEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Field is invalid: " + operation.getField());
            }

        }

        return null;

    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsInvoiceEntity.project.id;
                    projectExpression = addOrExpression(projectExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsInvoiceEntity.project.name;
                    projectExpression = addOrExpression(projectExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isCompleted" -> {
                    BooleanPath isCompletedPath = bsInvoiceEntity.project.isCompleted;
                    projectExpression = addOrExpression(projectExpression, getBooleanPathBooleanExpression(isCompletedPath, operation));
                }
                case "created" -> {
                    DatePath<Date> createdPath = entityPath.getDate(bsInvoiceEntity.project.created.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(createdPath, operation));
                }
                case "dueDate" -> {
                    DatePath<Date> dueDatePath = entityPath.getDate(bsInvoiceEntity.project.dueDate.toString(), java.util.Date.class);
                    projectExpression = addOrExpression(projectExpression, getDatePathBooleanExpression(dueDatePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }

        }

        return projectExpression;

    }

    private BooleanExpression getClientExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression clientExpression = null;

        for (FilterOperator operation : filter.getOperations()) {
            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> numberPath = bsInvoiceEntity.client.id;
                    clientExpression = addOrExpression(clientExpression, getNumberPathBooleanExpression(numberPath, operation));
                }
                case "firstName" -> {
                    StringPath stringPath = bsInvoiceEntity.client.firstName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));
                }
                case "lastName" -> {
                    StringPath lastNamePath = bsInvoiceEntity.client.lastName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(lastNamePath, operation));
                }
                case "username" -> {
                    StringPath usernamePath = bsInvoiceEntity.client.username;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(usernamePath, operation));
                }
                case "email" -> {
                    StringPath emailPath = bsInvoiceEntity.client.email;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(emailPath, operation));
                }
                case "isActive" -> {
                    BooleanPath booleanPath = bsInvoiceEntity.client.isActive;
                    clientExpression = addOrExpression(clientExpression, getBooleanPathBooleanExpression(booleanPath, operation));
                }
                case "lastLogin" -> {
                    DatePath<Date> datePath = entityPath.getDate(bsInvoiceEntity.client.lastLogin.toString(), Date.class);
                    clientExpression = addOrExpression(clientExpression, getDatePathBooleanExpression(datePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }

        }

        return clientExpression;
    }


}

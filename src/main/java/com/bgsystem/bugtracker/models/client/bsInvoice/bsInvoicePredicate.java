package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class bsInvoicePredicate extends CommonPathExpression<bsInvoiceEntity> {

    private final QbsInvoiceEntity bsInvoiceEntity = QbsInvoiceEntity.bsInvoiceEntity;

    public bsInvoicePredicate(){
        super( );
        this.entityPath = new PathBuilder<bsInvoiceEntity>(bsInvoiceEntity.class, "bsInvoiceEntity");
        this.entityFields.add("client");
        this.entityFields.add("project");
        this.entityFields.add("business");
        this.entityFields.add("task");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()) {
            case "client" -> getClientExpression(filter);
            case "project" -> getProjectExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };

    }

    private BooleanExpression getProjectExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectExpression = null;

        return projectExpression;

    }

    private BooleanExpression getClientExpression(FilterRequest filter) throws BadOperator {
        BooleanExpression clientExpression = null;
        for (FilterOperator operation : filter.getOperations()) {
            switch (operation.getField()) {
                case "id":
                    NumberPath<Long> numberPath = bsInvoiceEntity.client.id;
                    clientExpression = addOrExpression(clientExpression, getNumberPathBooleanExpression(numberPath, operation));
                    break;
                case "firstName":
                    StringPath stringPath = bsInvoiceEntity.client.firstName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));
                    break;
                case "lastName":
                    StringPath lastNamePath = bsInvoiceEntity.client.lastName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(lastNamePath, operation));
                    break;
                case "username":
                    StringPath usernamePath = bsInvoiceEntity.client.username;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(usernamePath, operation));
                    break;
                case "email":
                    StringPath emailPath = bsInvoiceEntity.client.email;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(emailPath, operation));
                    break;
                case "isActive":
                    BooleanPath booleanPath = bsInvoiceEntity.client.isActive;
                    clientExpression = addOrExpression(clientExpression, getBooleanPathBooleanExpression(booleanPath, operation));
                    break;
                case "lastLogin":
                    DatePath<Date> datePath = entityPath.getDate(bsInvoiceEntity.client.lastLogin.toString(), java.util.Date.class);
                    clientExpression = addOrExpression(clientExpression, getDatePathBooleanExpression(datePath, operation));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }
        return clientExpression;
    }


}

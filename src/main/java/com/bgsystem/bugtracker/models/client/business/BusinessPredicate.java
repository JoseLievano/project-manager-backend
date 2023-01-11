package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.models.HQ.client.ClientRepository;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class BusinessPredicate extends CommonPathExpression<BusinessEntity> {

    private final QBusinessEntity businessEntity = QBusinessEntity.businessEntity;
    private final ClientRepository clientRepository;

    public BusinessPredicate(ClientRepository clientRepository){
        super( );
        this.entityPath = new PathBuilder<BusinessEntity>(BusinessEntity.class, "businessEntity");

        this.entityFields.add("client");
        this.entityFields.add("plan");
        this.entityFields.add("invoice");

        this.clientRepository = clientRepository;
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()) {
            case "client" -> getClientExpression(filter);
            case "plan" -> getPlanExpression(filter);
            case "invoice" -> getInvoiceExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };
    }


    private BooleanExpression getInvoiceExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression invoiceExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> idPath = businessEntity.invoices.any().id;
                    invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "amount" -> {
                    NumberPath<Double> amountPath = businessEntity.invoices.any().amount;
                    invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(amountPath, operation));
                }
                case "number" -> {
                    StringPath numberPath = businessEntity.invoices.any().number;
                    invoiceExpression = addOrExpression(invoiceExpression, getStringPathBooleanExpression(numberPath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }

        }

        return invoiceExpression;
    }


    //Expressions for client field
    private BooleanExpression getClientExpression(FilterRequest filter) throws BadOperator {
        BooleanExpression clientExpression = null;
        for (FilterOperator operation : filter.getOperations()) {
            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> idPath = businessEntity.client.id;
                    clientExpression = addOrExpression(clientExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "email" -> {
                    StringPath emailPath = businessEntity.client.email;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(emailPath, operation));
                }
                case "firstName" -> {
                    StringPath firstNamePath = businessEntity.client.firstName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(firstNamePath, operation));
                }
                case "lastName" -> {
                    StringPath lastNamePath = businessEntity.client.lastName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(lastNamePath, operation));
                }
                case "username" -> {
                    StringPath usernamePath = businessEntity.client.username;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(usernamePath, operation));
                }
                case "lastLogin" -> {
                    DatePath<Date> datePath = entityPath.getDate(businessEntity.client.lastLoginDate.toString(), Date.class);
                    clientExpression = addOrExpression(clientExpression, getDatePathBooleanExpression(datePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }
        return clientExpression;
    }

    private BooleanExpression getPlanExpression(FilterRequest filter) throws BadOperator{
        BooleanExpression planExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> idPath = businessEntity.plan.id;
                    planExpression = addOrExpression(planExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = businessEntity.plan.name;
                    planExpression = addOrExpression(planExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "price" -> {
                    NumberPath<Double> pricePath = businessEntity.plan.price;
                    planExpression = addOrExpression(planExpression, getNumberPathBooleanExpression(pricePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field provided: " + operation.getField());
            }
        }
        return planExpression;
    }


}

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

        BooleanExpression expression = null;

        if (filter.getField().equals("client")){

            expression = getClientExpression(filter);

        }else if (filter.getField().equals("plan")){

            expression = getPlanExpression(filter);

        }else if (filter.getField().equals("invoice")){

            expression = getInvoiceExpression(filter);

        }

        return expression;

    }

    private BooleanExpression getInvoiceExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression invoiceExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = businessEntity.invoices.any().id;

                invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(numberPath, operation));

            } else if (operation.getField().equals("amount")) {

                NumberPath<Double> numberPath = businessEntity.invoices.any().amount;

                invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(numberPath, operation));

            }else if (operation.getField().equals("number")){

                StringPath stringPath = businessEntity.invoices.any().number;

                invoiceExpression = addOrExpression(invoiceExpression, getStringPathBooleanExpression(stringPath, operation));

            }

        }

        return invoiceExpression;

    }

    //Expressions for client field
    /*private BooleanExpression getClientExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression clientExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = businessEntity.client.id;

                clientExpression = addOrExpression(clientExpression, getNumberPathBooleanExpression(numberPath, operation));

            } else if (operation.getField().equals("email")){

                StringPath stringPath = businessEntity.client.email;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("firstName")) {

                StringPath stringPath = businessEntity.client.firstName;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("lastName")) {

                StringPath stringPath = businessEntity.client.lastName;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("username")) {

                StringPath stringPath = businessEntity.client.username;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }

        }

        return clientExpression;
    }*/
    private BooleanExpression getClientExpression(FilterRequest filter) throws BadOperator {
        BooleanExpression clientExpression = null;
        for (FilterOperator operation : filter.getOperations()) {
            switch (operation.getField()) {
                case "id":
                    NumberPath<Long> numberPath = businessEntity.client.id;
                    clientExpression = addOrExpression(clientExpression, getNumberPathBooleanExpression(numberPath, operation));
                    break;
                case "email":
                    StringPath stringPath = businessEntity.client.email;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));
                    break;
                case "firstName":
                    StringPath firstNamePath = businessEntity.client.firstName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(firstNamePath, operation));
                    break;
                case "lastName":
                    StringPath lastNamePath = businessEntity.client.lastName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(lastNamePath, operation));
                    break;
                case "username":
                    StringPath usernamePath = businessEntity.client.username;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(usernamePath, operation));
                    break;
                case "lastLogin":
                    DatePath<Date> datePath = entityPath.getDate(businessEntity.client.lastLoginDate.toString(), java.util.Date.class);
                    clientExpression = addOrExpression(clientExpression, getDatePathBooleanExpression(datePath, operation));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }
        return clientExpression;
    }

    private BooleanExpression getPlanExpression(FilterRequest filter) throws BadOperator{

        BooleanExpression planExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = businessEntity.plan.id;

                planExpression = addOrExpression(planExpression, getNumberPathBooleanExpression(numberPath, operation));

            }else if (operation.getField().equals("name")) {

                StringPath stringPath = businessEntity.plan.name;

                planExpression = addOrExpression(planExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("price")){

                NumberPath<Double> numberPath = businessEntity.plan.price;

                planExpression = addOrExpression(planExpression, getNumberPathBooleanExpression(numberPath, operation));

            }

        }

        return planExpression;

    }

}

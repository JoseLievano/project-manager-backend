package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvoicePredicate extends CommonPathExpression<InvoiceEntity> {

    private final QInvoiceEntity invoiceEntity = QInvoiceEntity.invoiceEntity;

    public InvoicePredicate(){
        super( );
        this.entityFields.add("client");
        this.entityPath = new PathBuilder<InvoiceEntity>(InvoiceEntity.class, "invoiceEntity");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression expression = null;

        if (filter.getField().equals("client")){

            expression = getClientExpression(filter);

        }else if (filter.getField().equals("plan")){

            expression = getPlanExpression(filter);

        }

        return expression;

    }

    private BooleanExpression getPlanExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression planExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = invoiceEntity.planEntity.id;

                planExpression = addOrExpression(planExpression, getNumberPathBooleanExpression(numberPath, operation));

            } else if (operation.getField().equals("name")) {

                StringPath stringPath = invoiceEntity.planEntity.name;

                planExpression = addOrExpression(planExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("price")){

                    NumberPath<Double> numberPath = invoiceEntity.planEntity.price;

                    planExpression = addOrExpression(planExpression, getNumberPathBooleanExpression(numberPath, operation));

            }

        }

        return planExpression;

    }


    private BooleanExpression getClientExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression clientExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = invoiceEntity.clientEntity.id;

                clientExpression = addOrExpression(clientExpression, getNumberPathBooleanExpression(numberPath, operation));

            } else if (operation.getField().equals("email")){

                StringPath stringPath = invoiceEntity.clientEntity.email;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("firstName")) {

                StringPath stringPath = invoiceEntity.clientEntity.firstName;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("lastName")) {

                StringPath stringPath = invoiceEntity.clientEntity.lastName;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("username")) {

                StringPath stringPath = invoiceEntity.clientEntity.username;

                clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("lastLoginDate")) {

                DatePath<Date> datePath = entityPath.getDate(invoiceEntity.clientEntity.lastLoginDate.toString(), java.util.Date.class);

                clientExpression = addOrExpression(clientExpression, getDatePathBooleanExpression(datePath, operation));

            }

        }

        return clientExpression;
    }

}

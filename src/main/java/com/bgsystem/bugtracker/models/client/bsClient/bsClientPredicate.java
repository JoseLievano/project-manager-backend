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
        BooleanExpression expression = null;
        if (filter.getField().equals("projects")){
            expression = getProjectsExpression(filter);
        }else if (filter.getField().equals("invoices")){
            expression = getInvoicesExpression(filter);
        }
        return expression;
    }

    private BooleanExpression getInvoicesExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression invoiceExpression = null;

        for (FilterOperator operator : filter.getOperations()){

            if (operator.getField().equals("id")){

                NumberPath<Long> numberPath = bsClientEntity.invoices.any().id;

                invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(numberPath, operator));

            }else if (operator.getField().equals("number")) {

                StringPath stringPath = bsClientEntity.invoices.any().number;

                invoiceExpression = addOrExpression(invoiceExpression, getStringPathBooleanExpression(stringPath, operator));

            }else if (operator.getField().equals("amount")) {

                NumberPath<Double> numberPath = bsClientEntity.invoices.any().amount;

                invoiceExpression = addOrExpression(invoiceExpression, getNumberPathBooleanExpression(numberPath, operator));

            }else if (operator.getField().equals("isPaid")){

                    BooleanPath booleanPath = bsClientEntity.invoices.any().isPaid;

                    invoiceExpression = addOrExpression(invoiceExpression, getBooleanPathBooleanExpression(booleanPath, operator));

            }else if (operator.getField().equals("isOverdue")) {

                BooleanPath booleanPath = bsClientEntity.invoices.any().isOverDue;

                invoiceExpression = addOrExpression(invoiceExpression, getBooleanPathBooleanExpression(booleanPath, operator));

            }

        }

        return invoiceExpression;

    }

    private BooleanExpression getProjectsExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression projectsExpression = null;

        for (FilterOperator operator : filter.getOperations()){

            if (operator.getField().equals("id")){

                NumberPath<Long> numberPath = bsClientEntity.projects.any().id;

                projectsExpression = addOrExpression(projectsExpression, getNumberPathBooleanExpression(numberPath, operator));

            }else if (operator.getField().equals("name")) {

                StringPath stringPath = bsClientEntity.projects.any().name;

                projectsExpression = addOrExpression(projectsExpression, getStringPathBooleanExpression(stringPath, operator));

            }else if (operator.getField().equals("isCompleted")) {

                BooleanPath booleanPath = bsClientEntity.projects.any().isCompleted;

                projectsExpression = addOrExpression(projectsExpression, getBooleanPathBooleanExpression(booleanPath, operator));
            }

        }

        return projectsExpression;

    }

}

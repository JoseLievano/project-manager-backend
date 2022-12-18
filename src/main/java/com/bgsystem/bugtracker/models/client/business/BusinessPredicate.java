package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;


@Service
public class BusinessPredicate extends CommonPathExpression<BusinessEntity> {

    private final QBusinessEntity businessEntity = QBusinessEntity.businessEntity;

    public BusinessPredicate(){
        super( );
        this.entityFields.add("client");
        this.entityPath = new PathBuilder<BusinessEntity>(BusinessEntity.class, "businessEntity");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) {

        BooleanExpression expression = null;

        if (filter.getField().equals("client")){
            expression = this.getClientExpression(filter);
        }

        return expression;

    }

    //Expressions for client field
    private BooleanExpression getClientExpression(FilterRequest filter){

        BooleanExpression clientExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (this.isANumber(operation.getValue())){
                clientExpression = businessEntity.client.id.eq(Long.parseLong(operation.getValue()));
            }else {

                String operator = operation.getOperator();

                if (operator.equals(":")) {

                    clientExpression = businessEntity.client.firstName.containsIgnoreCase(operation.getValue())
                            .or(businessEntity.client.lastName.containsIgnoreCase(operation.getValue()));

                } else if (operator.equals("=")) {

                    clientExpression = businessEntity.client.firstName.equalsIgnoreCase(operation.getValue())
                            .or(businessEntity.client.lastName.equalsIgnoreCase(operation.getValue()));

                } else if (operator.equals("!=")) {

                    clientExpression = businessEntity.client.firstName.notEqualsIgnoreCase(operation.getValue())
                            .and(businessEntity.client.lastName.notEqualsIgnoreCase(operation.getValue()));

                }
            }
        }

        return clientExpression;
    }


}

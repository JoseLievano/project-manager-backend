package com.bgsystem.bugtracker.models.client.bsEmployee;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsEmployeePredicate extends CommonPathExpression<bsEmployeeEntity> {

    private final QbsEmployeeEntity bsEmployeeEntity = QbsEmployeeEntity.bsEmployeeEntity;

    public bsEmployeePredicate(){
        super( );
        this.entityPath = new PathBuilder<bsEmployeeEntity>(bsEmployeeEntity.class, "bsEmployeeEntity");
        this.entityFields.add("business");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression expression = null;

        if (filter.getField().equals("business")){

            expression = getBusinessExpression(filter);

        }

        return expression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = bsEmployeeEntity.business.id;

                businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(numberPath, operation));

            } else if (operation.getField().equals("name")) {

                StringPath stringPath = bsEmployeeEntity.business.name;

                businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("taxID")){

                StringPath stringPath = bsEmployeeEntity.business.name;

                businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("pendingInvoice")){

                BooleanPath booleanPath = bsEmployeeEntity.business.pendingInvoice;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }else if (operation.getField().equals("overDue")){

                BooleanPath booleanPath = bsEmployeeEntity.business.overDue;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }else if (operation.getField().equals("isActive")){

                BooleanPath booleanPath = bsEmployeeEntity.business.isActive;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }

        }

        return businessExpression;

    }
}

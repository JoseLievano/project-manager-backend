package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsDocPredicate extends CommonPathExpression<bsDocEntity> {

    private final QbsDocEntity bsDocEntity = QbsDocEntity.bsDocEntity;

    public bsDocPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsDocEntity>(bsDocEntity.class, "bsDocEntity");

        this.entityFields.add("category");
        this.entityFields.add("business");

    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression expression = null;

        if (filter.getField().equals("category")) {

            expression = getCategoryExpression(filter);

        }else if (filter.getField().equals("business")) {

            expression = getBusinessExpression(filter);

        }

        return expression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = bsDocEntity.business.id;

                businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(numberPath, operation));

            }else if (operation.getField().equals("name")){

                StringPath stringPath = bsDocEntity.business.name;

                businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("taxID")){

                StringPath stringPath = bsDocEntity.business.taxID;

                businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("pendingInvoice")){

                BooleanPath booleanPath = bsDocEntity.business.pendingInvoice;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }else if (operation.getField().equals("overDue")){

                BooleanPath booleanPath = bsDocEntity.business.overDue;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }else if (operation.getField().equals("isActive")){

                BooleanPath booleanPath = bsDocEntity.business.isActive;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }

        }


        return businessExpression;

    }

    private BooleanExpression getCategoryExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression categoryExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")) {

                NumberPath<Long> numberPath = bsDocEntity.bsDocsCategory.id;

                categoryExpression = addOrExpression(categoryExpression, getNumberPathBooleanExpression(numberPath, operation));

            }else if (operation.getField().equals("name")) {

                StringPath stringPath = bsDocEntity.bsDocsCategory.name;

                categoryExpression = addOrExpression(categoryExpression, getStringPathBooleanExpression(stringPath, operation));

            }

        }

        return categoryExpression;

    }
}

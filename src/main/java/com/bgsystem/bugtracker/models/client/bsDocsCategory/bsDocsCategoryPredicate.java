package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsDocsCategoryPredicate extends CommonPathExpression<bsDocsCategoryEntity> {

    private final QbsDocsCategoryEntity bsDocsCategoryEntity = QbsDocsCategoryEntity.bsDocsCategoryEntity;
    private final BusinessRepository businessRepository;

    public bsDocsCategoryPredicate(BusinessRepository businessRepository){
        super();
        this.entityPath = new PathBuilder<bsDocsCategoryEntity>(bsDocsCategoryEntity.class, "bsDocsCategoryEntity");
        this.entityFields.add("business");
        this.entityFields.add("docs");
        this.businessRepository = businessRepository;
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        BooleanExpression expression = null;

        if (filter.getField().equals("business")){

            expression = getBusinessExpression(filter);

        } else if (filter.getField().equals("docs")) {

            expression = getDocsExpression(filter);

        }

        return expression;
    }

    private BooleanExpression getDocsExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression docsExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = bsDocsCategoryEntity.bsDocs.any().id;

                docsExpression = addOrExpression(docsExpression, getNumberPathBooleanExpression(numberPath, operation));

            }else if (operation.getField().equals("title")){

                StringPath stringPath = bsDocsCategoryEntity.bsDocs.any().title;

                docsExpression = addOrExpression(docsExpression, getStringPathBooleanExpression(stringPath, operation));

            }

        }

        return docsExpression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            if (operation.getField().equals("id")){

                NumberPath<Long> numberPath = bsDocsCategoryEntity.business.id;

                businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(numberPath, operation));

            }else if (operation.getField().equals("name")){

                StringPath stringPath = bsDocsCategoryEntity.business.name;

                businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("taxID")){

                StringPath stringPath = bsDocsCategoryEntity.business.taxID;

                businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(stringPath, operation));

            }else if (operation.getField().equals("pendingInvoice")){

                BooleanPath booleanPath = bsDocsCategoryEntity.business.pendingInvoice;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }else if (operation.getField().equals("overDue")){

                BooleanPath booleanPath = bsDocsCategoryEntity.business.overDue;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }else if (operation.getField().equals("isActive")){

                BooleanPath booleanPath = bsDocsCategoryEntity.business.isActive;

                businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(booleanPath, operation));

            }

        }

        return businessExpression;

    }
}

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

        return switch (filter.getField()){
            case "business" -> getBusinessExpression(filter);
            case "docs" -> getDocsExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getDocsExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression docsExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" ->{
                    NumberPath<Long> idPath = bsDocsCategoryEntity.bsDocs.any().id;
                    docsExpression = addOrExpression(docsExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "title" ->{
                    StringPath titlePath = bsDocsCategoryEntity.bsDocs.any().title;
                    docsExpression = addOrExpression(docsExpression, getStringPathBooleanExpression(titlePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field in get doc expression: " + filter.getField());
            }
        }
        return docsExpression;
    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" ->{
                    NumberPath<Long> idPath = bsDocsCategoryEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" ->{
                    StringPath namePath = bsDocsCategoryEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" ->{
                    StringPath taxIDPath = bsDocsCategoryEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" ->{
                    BooleanPath pendingInvoicePath = bsDocsCategoryEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" ->{
                    BooleanPath overDuePath = bsDocsCategoryEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" ->{
                    BooleanPath isActivePath = bsDocsCategoryEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
            }
        }

        return businessExpression;

    }
}

package com.bgsystem.bugtracker.models.client.bsKB;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsKBPredicate extends CommonPathExpression<bsKBEntity> {

    private final QbsKBEntity bsKBEntity = QbsKBEntity.bsKBEntity;

    public bsKBPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsKBEntity>(bsKBEntity.class, "bsKBEntity");
        this.entityFields.add("category");
        this.entityFields.add("business");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()) {
            case "category" -> getbsKBCategoryExpression(filter);
            case "business" -> getBusinessExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };
    }

    private BooleanExpression getbsKBCategoryExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression bsKBCategoryExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> idPath = bsKBEntity.bsKBCategory.id;
                    bsKBCategoryExpression = addOrExpression(bsKBCategoryExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsKBEntity.bsKBCategory.name;
                    bsKBCategoryExpression = addOrExpression(bsKBCategoryExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }

        return bsKBCategoryExpression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator{

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsKBEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsKBEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsKBEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoicePath = bsKBEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsKBEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsKBEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }

        return businessExpression;

    }
}

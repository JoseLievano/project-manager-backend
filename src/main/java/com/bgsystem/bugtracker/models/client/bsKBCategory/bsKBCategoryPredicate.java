package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsKBCategoryPredicate extends CommonPathExpression<bsKBCategoryEntity> {

    private final QbsKBCategoryEntity bsKBCategoryEntity = QbsKBCategoryEntity.bsKBCategoryEntity;

    public bsKBCategoryPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsKBCategoryEntity>(bsKBCategoryEntity.class, "bsKBCategoryEntity");
        this.entityFields.add("business");
        this.entityFields.add("kbs");
        this.entityFields.add("parentCategory");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()) {
            case "business" -> getBusinessExpression(filter);
            case "kbs" -> getbsKBExpression(filter);
            case "parentCategory" -> getParentCategory(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };
    }

    private BooleanExpression getbsKBExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression bsKBExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsKBCategoryEntity.bsKBEntities.any().id;
                    bsKBExpression = addOrExpression(bsKBExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "title" -> {
                    StringPath titlePath = bsKBCategoryEntity.bsKBEntities.any().title;
                    bsKBExpression = addOrExpression(bsKBExpression, getStringPathBooleanExpression(titlePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }

        return bsKBExpression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> idPath = bsKBCategoryEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsKBCategoryEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsKBCategoryEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" ->{
                    BooleanPath pendingInvoicePath = bsKBCategoryEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsKBCategoryEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsKBCategoryEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + operation.getField());
            }
        }
        return businessExpression;
    }

    private BooleanExpression getParentCategory(FilterRequest filter) throws BadOperator {

        BooleanExpression parentExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsKBCategoryEntity.parentKB.id;
                    parentExpression = addOrExpression(parentExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsKBCategoryEntity.parentKB.name;
                    parentExpression = addOrExpression(parentExpression, getStringPathBooleanExpression(namePath, operation));
                }
            }

        }

        return parentExpression;

    }

}

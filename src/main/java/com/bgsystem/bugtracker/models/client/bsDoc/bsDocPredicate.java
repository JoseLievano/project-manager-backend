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
        return switch (filter.getField()){
            case "category" -> getCategoryExpression(filter);
            case "business" -> getBusinessExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field" + filter.getField());
        };
    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {
        BooleanExpression businessExpression = null;
        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()) {
                case "id" -> {
                    NumberPath<Long> idNumberPath = bsDocEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idNumberPath, operation));
                }
                case "name" -> {
                    StringPath nameStringPath = bsDocEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(nameStringPath, operation));
                }
                case "taxID" -> {
                    StringPath taxIdStringPath = bsDocEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIdStringPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoiceBooleanPath = bsDocEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoiceBooleanPath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDueBooleanPath = bsDocEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDueBooleanPath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActiveBooleanPath = bsDocEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActiveBooleanPath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field " + operation.getField());
            }
        }
        return businessExpression;
    }


    private BooleanExpression getCategoryExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression categoryExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsDocEntity.bsDocsCategory.id;
                    categoryExpression = addOrExpression(categoryExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsDocEntity.bsDocsCategory.name;
                    categoryExpression = addOrExpression(categoryExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid Field " + operation.getField());
            }

        }

        return categoryExpression;

    }
}

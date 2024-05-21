package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

@Service
public class bsManagerPredicate extends CommonPathExpression<bsManagerEntity> {

    private final QbsManagerEntity bsManagerEntity = QbsManagerEntity.bsManagerEntity;

    public bsManagerPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsManagerEntity>(bsManagerEntity.class, "bsManagerEntity");
        this.entityFields.add("business");
    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return switch (filter.getField()) {
            case "business" -> getBusinessExpression(filter);
            default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
        };
    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (filter.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsManagerEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsManagerEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsManagerEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoicePath = bsManagerEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsManagerEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsManagerEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Invalid field: " + filter.getField());
            }
        }

        return businessExpression;

    }
}

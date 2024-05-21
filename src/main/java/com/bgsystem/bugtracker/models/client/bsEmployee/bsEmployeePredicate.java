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

        return switch (filter.getField()){
            case "business" -> getBusinessExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };
    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){

            switch (operation.getField()){
                case "id" ->{
                    NumberPath<Long> idPath = bsEmployeeEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" ->{
                    StringPath namePath = bsEmployeeEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" ->{
                    StringPath taxIDPath = bsEmployeeEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "pendingInvoice" ->{
                    BooleanPath pendingInvoicePath = bsEmployeeEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" ->{
                    BooleanPath overDuePath = bsEmployeeEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" ->{
                    BooleanPath isActivePath = bsEmployeeEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }
        return businessExpression;

    }
}

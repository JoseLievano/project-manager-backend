package com.bgsystem.bugtracker.models.client.business;

import com.querydsl.core.types.dsl.BooleanExpression;

public class BusinessExpressions {

    public static BooleanExpression nameContains(String name) {
        return name != null ? QBusinessEntity.businessEntity.name.containsIgnoreCase(name) : null;
    }

    public static BooleanExpression taxIDContains(String taxID) {
        return taxID != null ? QBusinessEntity.businessEntity.taxID.containsIgnoreCase(taxID) : null;
    }

    public static BooleanExpression clientFirstNameContains(String client) {
        return client != null ? QBusinessEntity.businessEntity.client.firstName.containsIgnoreCase(client) : null;
    }

    public static BooleanExpression clientLastNameContains(String client) {
        return client != null ? QBusinessEntity.businessEntity.client.lastName.containsIgnoreCase(client) : null;
    }

    public static BooleanExpression planContains(String plan) {
        return plan != null ? QBusinessEntity.businessEntity.plan.name.containsIgnoreCase(plan) : null;
    }

}

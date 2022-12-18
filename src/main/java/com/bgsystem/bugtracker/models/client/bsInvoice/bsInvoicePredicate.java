package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsInvoicePredicate extends CommonPathExpression<bsInvoiceEntity> {

    private final QbsInvoiceEntity bsInvoiceEntity = QbsInvoiceEntity.bsInvoiceEntity;

    public bsInvoicePredicate(){
        super( );
        this.entityPath = new PathBuilder<bsInvoiceEntity>(bsInvoiceEntity.class, "bsInvoiceEntity");
    }

}

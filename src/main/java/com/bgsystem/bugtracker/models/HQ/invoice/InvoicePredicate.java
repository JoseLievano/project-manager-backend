package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class InvoicePredicate extends CommonPathExpression<InvoiceEntity> {

    private final QInvoiceEntity invoiceEntity = QInvoiceEntity.invoiceEntity;

    public InvoicePredicate(){
        super( );
        this.entityPath = new PathBuilder<InvoiceEntity>(InvoiceEntity.class, "invoiceEntity");
    }

}

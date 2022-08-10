package com.bgsystem.bugtracker.models.client.bsInvoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsInvoiceRepository extends JpaRepository <bsInvoiceEntity, Long> {

    Set<bsInvoiceEntity> findByIsPaidIsFalseOrderByLimitDate ();

}

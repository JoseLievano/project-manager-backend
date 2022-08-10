package com.bgsystem.bugtracker.models.client.bsInvoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsInvoiceRepository extends JpaRepository <bsInvoiceEntity, Long> {



}

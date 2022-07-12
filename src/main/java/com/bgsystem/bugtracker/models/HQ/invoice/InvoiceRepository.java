package com.bgsystem.bugtracker.models.HQ.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    Set<InvoiceEntity> findByNumber (String number);

}

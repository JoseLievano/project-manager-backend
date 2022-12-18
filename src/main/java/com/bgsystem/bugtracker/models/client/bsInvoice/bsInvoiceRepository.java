package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsInvoiceRepository extends DefaultRepository<bsInvoiceEntity, Long> {

    Set<bsInvoiceEntity> findByIsPaidIsFalseOrderByLimitDate ();

}

package com.bgsystem.bugtracker.models.HQ.invoice;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface InvoiceRepository extends DefaultRepository<InvoiceEntity, Long> {

    Set<InvoiceEntity> findByNumber (String number);

}

package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMiniDTO;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Set;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ClientForm {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Boolean isActive;

    private Date dateCreated;

    private Date lastLoginDate;

    private Long mainHQ;

    private Set<Long> business;

    private Set<Long> invoice;

}

package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceEntity;
import com.bgsystem.bugtracker.models.HQ.invoice.InvoiceMiniDTO;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> roles;

    private String username;

    private Boolean isActive;

    private Date dateCreated;

    private Date lastLoginDate;

    private Set<BusinessMiniDTO> businesses;

    private Set<InvoiceMiniDTO> invoice;

}

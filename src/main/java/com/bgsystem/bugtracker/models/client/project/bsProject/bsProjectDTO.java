package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMiniDTO;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMiniDTO;
import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrKB.bsPrKBMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMiniDTO;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsProjectDTO {

    private Long id;

    private String name;

    private Boolean isCompleted;

    private Date created;

    private Date dueDate;

    private BusinessMiniDTO business;

    private bsClientMiniDTO client;

    private Set<bsPrTaskMiniDTO> tasks;

    private Set<bsInvoiceMiniDTO> invoices;

    private Set<bsPrChannelMiniDTO> channels;

    private Set<bsPrDocsCategoryMiniDTO> docsCategories;

    private Set<bsPrDocsMiniDTO> docs;

    private Set<bsPrKBCategoryMiniDTO> kbCategories;

    private Set<bsPrKBMiniDTO> kbs;

}

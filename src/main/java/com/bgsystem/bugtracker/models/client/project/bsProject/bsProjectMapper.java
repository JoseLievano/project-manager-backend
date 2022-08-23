package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMapper;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsProjectMapper implements DefaultMapper <bsProjectDTO, bsProjectMiniDTO, bsProjectForm, bsProjectEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Lazy
    @Autowired
    private bsClientMapper clientMapper;

    @Lazy
    @Autowired
    private bsPrTaskMapper taskMapper;

    @Lazy
    @Autowired
    private bsInvoiceMapper invoiceMapper;

    @Lazy
    @Autowired
    private bsPrChannelMapper channelMapper;

    @Lazy
    @Autowired
    private bsPrDocsCategoryMapper docsCategoryMapper;

    @Lazy
    @Autowired
    private bsPrDocsMapper docsMapper;

    @Override
    public bsProjectDTO toDTO(bsProjectEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsProjectDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isCompleted(entity.getIsCompleted())
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .client(clientMapper.toSmallDTO(entity.getClient()))
                .tasks(entity.getTasks().stream()
                        .map(taskMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .invoices(entity.getInvoices().stream()
                        .map(invoiceMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .channels(entity.getChannels().stream()
                        .map(channelMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .docsCategories(entity.getDocsCategories().stream()
                        .map(docsCategoryMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .docs(entity.getDocs().stream()
                        .map(docsMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();

    }

    @Override
    public bsProjectMiniDTO toSmallDTO(bsProjectEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsProjectMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isCompleted(entity.getIsCompleted())
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
                .build();

    }

    @Override
    public bsProjectEntity toEntity(bsProjectForm form) {

        if (form == null) {
            return null;
        }

        return bsProjectEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .isCompleted(form.getIsCompleted())
                .created(form.getCreated())
                .dueDate(form.getDueDate())
                .build();
    }
}

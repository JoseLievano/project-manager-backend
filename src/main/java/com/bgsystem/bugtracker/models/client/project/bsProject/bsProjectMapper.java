package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMapper;
import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrKB.bsPrKBMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsProjectMapper implements DefaultMapper <bsProjectDTO, bsProjectMiniDTO, bsProjectListDTO, bsProjectForm, bsProjectEntity> {

    private final BusinessMapper businessMapper;

    private final bsClientMapper clientMapper;

    private final bsPrTaskMapper taskMapper;

    private final bsInvoiceMapper invoiceMapper;

    private final bsPrChannelMapper channelMapper;

    private final bsPrDocsCategoryMapper docsCategoryMapper;

    private final bsPrDocsMapper docsMapper;

    private final bsPrKBCategoryMapper kbCategoryMapper;

    private final bsPrKBMapper kbMapper;

    @Lazy
    @Autowired
    public bsProjectMapper(BusinessMapper businessMapper,
                           bsClientMapper clientMapper,
                           bsPrTaskMapper taskMapper,
                           bsInvoiceMapper invoiceMapper,
                           bsPrChannelMapper channelMapper,
                           bsPrDocsCategoryMapper docsCategoryMapper,
                           bsPrDocsMapper docsMapper,
                           bsPrKBCategoryMapper kbCategoryMapper,
                           bsPrKBMapper kbMapper) {
        this.businessMapper = businessMapper;
        this.clientMapper = clientMapper;
        this.taskMapper = taskMapper;
        this.invoiceMapper = invoiceMapper;
        this.channelMapper = channelMapper;
        this.docsCategoryMapper = docsCategoryMapper;
        this.docsMapper = docsMapper;
        this.kbCategoryMapper = kbCategoryMapper;
        this.kbMapper = kbMapper;
    }

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
                .kbCategories(entity.getKbCategories().stream()
                        .map(kbCategoryMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                ).kbs(entity.getKbs().stream()
                        .map(kbMapper::toSmallDTO)
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

    @Override
    public bsProjectListDTO toListDTO(bsProjectEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsProjectListDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isCompleted(entity.getIsCompleted())
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .client(clientMapper.toSmallDTO(entity.getClient()))
                .taskCount(entity.getTaskCount())
                .invoiceCount(entity.getInvoiceCount())
                .channelCount(entity.getChannelCount())
                .docsCategoryCount(entity.getDocsCategoryCount())
                .docsCount(entity.getDocsCount())
                .kbCategoryCount(entity.getKbCategoryCount())
                .kbCount(entity.getKbCount())
                .build();

    }
}

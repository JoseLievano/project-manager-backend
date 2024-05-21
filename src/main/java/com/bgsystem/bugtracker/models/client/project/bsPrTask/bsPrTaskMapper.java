package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceMapper;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityMapper;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusMapper;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryMapper;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsPrTaskMapper implements DefaultMapper <bsPrTaskDTO, bsPrTaskMiniDTO, bsPrTaskListDTO, bsPrTaskForm, bsPrTaskEntity> {

    private final BusinessMapper businessMapper;

    private final bsProjectMapper bsProjectMapper;

    private final bsTaskCategoryMapper bsTaskCategoryMapper;

    private final bsTypeMapper bsTypeMapper;

    private final bsPriorityMapper bsPriorityMapper;

    private final bsStatusMapper bsStatusMapper;

    private final bsInvoiceMapper bsInvoiceMapper;

    @Lazy
    @Autowired
    public bsPrTaskMapper(
            BusinessMapper businessMapper,
            bsProjectMapper bsProjectMapper,
            bsTaskCategoryMapper bsTaskCategoryMapper,
            bsTypeMapper bsTypeMapper,
            bsPriorityMapper bsPriorityMapper,
            bsStatusMapper bsStatusMapper,
            bsInvoiceMapper bsInvoiceMapper
    ) {
        this.businessMapper = businessMapper;
        this.bsProjectMapper = bsProjectMapper;
        this.bsTaskCategoryMapper = bsTaskCategoryMapper;
        this.bsTypeMapper = bsTypeMapper;
        this.bsPriorityMapper = bsPriorityMapper;
        this.bsStatusMapper = bsStatusMapper;
        this.bsInvoiceMapper = bsInvoiceMapper;
    }

    @Override
    public bsPrTaskDTO toDTO(bsPrTaskEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrTaskDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
                .isInternal(entity.getIsInternal())
                .isOverDue(entity.getIsOverDue())
                .isDone(entity.getIsDone())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .category(bsTaskCategoryMapper.toSmallDTO(entity.getCategory()))
                .project(bsProjectMapper.toSmallDTO(entity.getProject()))
                .type(bsTypeMapper.toSmallDTO(entity.getType()))
                .priority(bsPriorityMapper.toSmallDTO(entity.getPriority()))
                .status(bsStatusMapper.toSmallDTO(entity.getStatus()))
                .invoice(bsInvoiceMapper.toSmallDTO(entity.getInvoice()))
                .build();

    }

    @Override
    public bsPrTaskMiniDTO toSmallDTO(bsPrTaskEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrTaskMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
                .isInternal(entity.getIsInternal())
                .isOverDue(entity.getIsOverDue())
                .isDone(entity.getIsDone())
                .build();

    }

    @Override
    public bsPrTaskEntity toEntity(bsPrTaskForm form) {

        if (form == null) {
            return null;
        }

        return bsPrTaskEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .created(form.getCreated())
                .dueDate(form.getDueDate())
                .isInternal(form.getIsInternal())
                .isOverDue(form.getIsOverDue())
                .isDone(form.getIsDone())
                .build();

    }

    @Override
    public bsPrTaskListDTO toListDTO(bsPrTaskEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrTaskListDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
                .isInternal(entity.getIsInternal())
                .isOverDue(entity.getIsOverDue())
                .isDone(entity.getIsDone())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .category(bsTaskCategoryMapper.toSmallDTO(entity.getCategory()))
                .project(bsProjectMapper.toSmallDTO(entity.getProject()))
                .type(bsTypeMapper.toSmallDTO(entity.getType()))
                .priority(bsPriorityMapper.toSmallDTO(entity.getPriority()))
                .status(bsStatusMapper.toSmallDTO(entity.getStatus()))
                .invoice(bsInvoiceMapper.toSmallDTO(entity.getInvoice()))
                .channelCount(entity.getChannelCount())
                .build();

    }
}

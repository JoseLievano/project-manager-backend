package com.bgsystem.bugtracker.models.client.project.bsPrTask;

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
public class bsPrTaskMapper implements DefaultMapper <bsPrTaskDTO, bsPrTaskMiniDTO, bsPrTaskForm, bsPrTaskEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Lazy
    @Autowired
    private bsProjectMapper bsProjectMapper;

    @Lazy
    @Autowired
    private bsTaskCategoryMapper bsTaskCategoryMapper;

    @Lazy
    @Autowired
    private bsTypeMapper bsTypeMapper;

    @Lazy
    @Autowired
    private bsPriorityMapper bsPriorityMapper;

    @Lazy
    @Autowired
    private bsStatusMapper bsStatusMapper;

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
}

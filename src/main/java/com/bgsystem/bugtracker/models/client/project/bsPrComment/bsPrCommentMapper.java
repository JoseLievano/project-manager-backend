package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import com.bgsystem.bugtracker.shared.models.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsPrCommentMapper implements DefaultMapper <bsPrCommentDTO, bsPrCommentMiniDTO, bsPrCommentForm, bsPrCommentEntity> {

    @Lazy
    @Autowired
    private UserMapper userMapper;

    @Lazy
    @Autowired
    private bsPrChannelMapper bsPrChannelMapper;

    @Lazy
    @Autowired
    private bsProjectMapper bsProjectMapper;

    @Override
    public bsPrCommentDTO toDTO(bsPrCommentEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrCommentDTO.builder()
                .id(entity.getId())
                .commentContent(entity.getCommentContent())
                .commentDate(entity.getCommentDate())
                .author(userMapper.toSmallDTO(entity.getAuthor()))
                .channel(bsPrChannelMapper.toSmallDTO(entity.getChannel()))
                .project(bsProjectMapper.toSmallDTO(entity.getProject()))
                .build();
    }

    @Override
    public bsPrCommentMiniDTO toSmallDTO(bsPrCommentEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrCommentMiniDTO.builder()
                .id(entity.getId())
                .commentContent(entity.getCommentContent())
                .commentDate(entity.getCommentDate())
                .build();
    }

    @Override
    public bsPrCommentEntity toEntity(bsPrCommentForm form) {

        if (form == null) {
            return null;
        }

        return bsPrCommentEntity.builder()
                .id(form.getId())
                .commentContent(form.getCommentContent())
                .commentDate(form.getCommentDate())
                .build();

    }
}

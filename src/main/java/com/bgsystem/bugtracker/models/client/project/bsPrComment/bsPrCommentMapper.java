package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import com.bgsystem.bugtracker.shared.models.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsPrCommentMapper implements DefaultMapper <bsPrCommentDTO, bsPrCommentMiniDTO, bsPrCommentListDTO, bsPrCommentForm, bsPrCommentEntity> {

    private final UserMapper userMapper;

    private final bsPrChannelMapper bsPrChannelMapper;

    private final bsProjectMapper bsProjectMapper;

    private final bsPrMentionMapper bsPrMentionMapper;

    @Lazy
    @Autowired
    public bsPrCommentMapper(UserMapper userMapper, bsPrChannelMapper bsPrChannelMapper, bsProjectMapper bsProjectMapper, bsPrMentionMapper bsPrMentionMapper) {
        this.userMapper = userMapper;
        this.bsPrChannelMapper = bsPrChannelMapper;
        this.bsProjectMapper = bsProjectMapper;
        this.bsPrMentionMapper = bsPrMentionMapper;
    }

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
                .mentions(entity.getMentions().stream()
                        .map(bsPrMentionMapper::toDTO)
                        .collect(java.util.stream.Collectors.toSet())
                )
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

    @Override
    public bsPrCommentListDTO toListDTO(bsPrCommentEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrCommentListDTO.builder()
                .id(entity.getId())
                .commentContent(entity.getCommentContent())
                .commentDate(entity.getCommentDate())
                .author(userMapper.toSmallDTO(entity.getAuthor()))
                .channel(bsPrChannelMapper.toSmallDTO(entity.getChannel()))
                .project(bsProjectMapper.toSmallDTO(entity.getProject()))
                .mentionCount(entity.getMentionCount())
                .build();

    }
}

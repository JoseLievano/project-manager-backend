package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import com.bgsystem.bugtracker.shared.models.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrMentionMapper implements DefaultMapper <bsPrMentionDTO, bsPrMentionMiniDTO, bsPrMentionForm, bsPrMentionEntity> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private bsPrCommentMapper commentMapper;

    @Override
    public bsPrMentionDTO toDTO(bsPrMentionEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrMentionDTO.builder()
                .id(entity.getId())
                .mentionDate(entity.getMentionDate())
                .author(userMapper.toSmallDTO(entity.getAuthor()))
                .mentionedUser(userMapper.toSmallDTO(entity.getMentionedUser()))
                .comment(commentMapper.toSmallDTO(entity.getComment()))
                .build();

    }

    @Override
    public bsPrMentionMiniDTO toSmallDTO(bsPrMentionEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrMentionMiniDTO.builder()
                .id(entity.getId())
                .mentionDate(entity.getMentionDate())
                .build();

    }

    @Override
    public bsPrMentionEntity toEntity(bsPrMentionForm form) {

        if (form == null) {
            return null;
        }

        return bsPrMentionEntity.builder()
                .id(form.getId())
                .mentionDate(form.getMentionDate())
                .build();

    }
}

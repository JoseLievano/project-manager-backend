package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import com.bgsystem.bugtracker.shared.models.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsPrMentionMapper implements DefaultMapper <bsPrMentionDTO, bsPrMentionMiniDTO, bsPrMentionListDTO, bsPrMentionForm, bsPrMentionEntity> {

    private final UserMapper userMapper;

    private final bsPrCommentMapper commentMapper;

    @Lazy
    @Autowired
    public bsPrMentionMapper(UserMapper userMapper,
                             bsPrCommentMapper commentMapper){
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

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

    @Override
    public bsPrMentionListDTO toListDTO(bsPrMentionEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrMentionListDTO.builder()
                .id(entity.getId())
                .mentionDate(entity.getMentionDate())
                .author(userMapper.toSmallDTO(entity.getAuthor()))
                .mentionedUser(userMapper.toSmallDTO(entity.getMentionedUser()))
                .comment(commentMapper.toSmallDTO(entity.getComment()))
                .build();
    }
}

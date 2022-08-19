package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import com.bgsystem.bugtracker.shared.models.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsPrChannelMapper implements DefaultMapper <bsPrChannelDTO, bsPrChannelMiniDTO, bsPrChannelForm, bsPrChannelEntity> {

    @Lazy
    @Autowired
    private bsProjectMapper projectMapper;

    @Lazy
    @Autowired
    private UserMapper userMapper;

    @Override
    public bsPrChannelDTO toDTO(bsPrChannelEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrChannelDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isPublic(entity.getIsPublic())
                .creationDate(entity.getCreationDate())
                .description(entity.getDescription())
                .author(userMapper.toSmallDTO(entity.getAuthor()))
                .project(projectMapper.toSmallDTO(entity.getProject()))
                .members(entity.getMembers().stream()
                        .map(userMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();

    }

    @Override
    public bsPrChannelMiniDTO toSmallDTO(bsPrChannelEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrChannelMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isPublic(entity.getIsPublic())
                .creationDate(entity.getCreationDate())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public bsPrChannelEntity toEntity(bsPrChannelForm form) {

        if (form == null) {
            return null;
        }

        return bsPrChannelEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .isPublic(form.getIsPublic())
                .creationDate(form.getCreationDate())
                .description(form.getDescription())
                .build();

    }
}

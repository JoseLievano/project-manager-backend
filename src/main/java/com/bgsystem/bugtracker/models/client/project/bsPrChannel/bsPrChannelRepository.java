package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsPrChannelRepository extends JpaRepository <bsPrChannelEntity, Long> {

    Set<bsPrChannelEntity> findByNameAndProject(String name, bsProjectEntity project);

    Boolean existsByNameAndProject(String name, bsProjectEntity project);

}

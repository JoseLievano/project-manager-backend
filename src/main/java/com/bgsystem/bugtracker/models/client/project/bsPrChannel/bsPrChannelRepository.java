package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsPrChannelRepository extends JpaRepository <bsPrChannelEntity, Long> {
}

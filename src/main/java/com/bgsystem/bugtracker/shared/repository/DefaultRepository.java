package com.bgsystem.bugtracker.shared.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DefaultRepository <ENTITY, ID> extends JpaRepository<ENTITY, ID>, QuerydslPredicateExecutor<ENTITY> {

}



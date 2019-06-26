package com.innowise.coordination.repository;

import com.innowise.coordination.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity, L> extends JpaRepository<E, L> {
}

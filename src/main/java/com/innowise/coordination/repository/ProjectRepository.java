package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends AbstractRepository<Project, Long> {

    List<Project> findByCustomerId(Long id);

    List<Project> findByStartDateNotNull();
}

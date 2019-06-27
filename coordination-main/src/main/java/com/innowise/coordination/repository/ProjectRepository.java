package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Project;
import com.innowise.coordination.entity.QProject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends AbstractRepository<Project, Long, QProject> {

    List<Project> findByCustomerId(Long id);

    List<Project> findByStartDateNotNullAndEndDateNull();
}

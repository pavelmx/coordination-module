package com.innowise.coordination.repository;

import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.entity.QProjectPositionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPositionEmployeeRepository
        extends AbstractRepository<ProjectPositionEmployee, Long, QProjectPositionEmployee> {
}

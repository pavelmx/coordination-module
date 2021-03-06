package com.innowise.coordination.repository;

import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.entity.QProjectPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectPositionRepository extends AbstractRepository<ProjectPosition, Long, QProjectPosition> {

    Page<ProjectPosition> findByProjectPositionEmployee_EmployeeId(Long id, Pageable pageable);

    List<ProjectPosition> findByProjectPositionEmployee_EmployeeId(Long id);

}

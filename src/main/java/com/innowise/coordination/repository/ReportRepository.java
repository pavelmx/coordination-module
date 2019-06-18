package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends AbstractRepository<Report, Long> {
}

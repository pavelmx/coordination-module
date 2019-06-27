package com.innowise.coordination.repository;

import com.innowise.coordination.entity.QReport;
import com.innowise.coordination.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends AbstractRepository<Report, Long, QReport> {
    List<Report> findByEmployeeId(Long employee_id);
}

package com.innowise.coordination.repository.postgres;

import com.innowise.coordination.entity.Report;
import com.innowise.coordination.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ReportRepositoryTests extends AbstractRepositoryTests<Report, ReportRepository, Long> {

    @Autowired
    private ReportRepository repository;

    private Report report;

    @Override
    public Report getEntity() {
        report = new Report();
        report = repository.save(report);
        return report;
    }

    @Override
    public Long getEntityId() {
        return report.getId();
    }


}

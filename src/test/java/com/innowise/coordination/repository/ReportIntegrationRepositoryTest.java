package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;


public class ReportIntegrationRepositoryTest extends AbstractIntegrationRepositoryTest<Report, ReportRepository, Long> {

    @Autowired
    private ReportRepository repository;

    private Report report;

    @Override
    public Report getEntity() {
        report = new Report();
        return report;
    }



}

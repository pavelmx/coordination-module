package com.innowise.coordination.service;

import com.innowise.coordination.entity.Report;
import com.innowise.coordination.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService extends AbstractService<Report, ReportRepository> {

    public ReportService(ReportRepository repository) {
        super(repository);
    }
}

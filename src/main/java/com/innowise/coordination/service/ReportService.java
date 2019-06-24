package com.innowise.coordination.service;

import com.innowise.coordination.entity.Report;
import com.innowise.coordination.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService extends AbstractService<Report, ReportRepository> {

    @Autowired
    private ReportRepository repository;

    public ReportService(ReportRepository repository) {
        super(repository);
    }

    @Override
    public List<Report> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "reportDate"));
    }
}

package com.innowise.coordination.service;

import com.google.common.collect.Lists;
import com.innowise.coordination.entity.QReport;
import com.innowise.coordination.entity.Report;
import com.innowise.coordination.entity.ReportType;
import com.innowise.coordination.filter.ReportFilter;
import com.innowise.coordination.repository.ReportRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService extends AbstractService<Report, ReportRepository, QReport> {

    @Autowired
    private ReportRepository repository;

    public ReportService(ReportRepository repository) {
        super(repository);
    }

    @Override
    public List<Report> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "reportDate"));
    }

    public List<Report> getAllByEmployeeId(Long employee_id) {
        return repository.findByEmployeeId(employee_id);
    }

    public List<Report> getAllWithFilter(ReportFilter filter){
        return Lists.newArrayList(repository.findAll(getPredicate(filter)));
    }

    private Predicate getPredicate(ReportFilter filter){
        BooleanBuilder predicate = new BooleanBuilder();
        QReport qReport = QReport.report;
        if (!StringUtils.isEmpty(filter.getDescriptionTask())) {
            predicate.and(qReport.descriptionTask.containsIgnoreCase(filter.getDescriptionTask()));
        }
        if (!StringUtils.isEmpty(filter.getReportType())) {
            predicate.and(qReport.reportType.eq(ReportType.valueOf(filter.getReportType())));
        }
        if (!StringUtils.isEmpty(filter.getTask())) {
            predicate.and(qReport.task.containsIgnoreCase(filter.getTask()));
        }
        if (!StringUtils.isEmpty(filter.getHoursForTask())) {
            predicate.and(qReport.hoursForTask.like(filter.getHoursForTask()));
        }
        if (!StringUtils.isEmpty(filter.getEmployeeId())) {
            predicate.and(qReport.employeeId.eq(Long.valueOf(filter.getEmployeeId())));
        }
        if (!StringUtils.isEmpty(filter.getProjectId())) {
            predicate.and(qReport.employeeId.eq(Long.valueOf(filter.getProjectId())));
        }
        if (!StringUtils.isEmpty(filter.getFirstDate()) && !StringUtils.isEmpty(filter.getLastDate())) {
            predicate.and(qReport.reportDate.between(LocalDate.parse(filter.getFirstDate()), LocalDate.parse(filter.getLastDate())));
        }else if (!StringUtils.isEmpty(filter.getFirstDate())) {
            predicate.and(qReport.reportDate.after(LocalDate.parse(filter.getFirstDate()).minusDays(1)));
        }else if(!StringUtils.isEmpty(filter.getLastDate())){
            predicate.and(qReport.reportDate.before(LocalDate.parse(filter.getLastDate()).plusDays(1)));
        }
        if (!StringUtils.isEmpty(filter.getShowFor())) {
            if(filter.getShowFor().contentEquals("month")){
                predicate.and(qReport.reportDate.month().eq(Integer.valueOf(filter.getMonth())));//current month
            }else if(filter.getShowFor().contentEquals("today")) {
                predicate.and(qReport.reportDate.eq(LocalDate.now()));
            }
        }
        return predicate;
    }
}

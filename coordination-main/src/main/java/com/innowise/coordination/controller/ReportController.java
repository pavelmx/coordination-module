package com.innowise.coordination.controller;

import com.innowise.coordination.entity.Report;
import com.innowise.coordination.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController extends AbstractController<Report, ReportService>{

    @Autowired
    private ReportService reportService;

    protected ReportController(ReportService service) {
        super(service);
    }

    @GetMapping("/employee")
    public List<Report> getAllByEmployeeId(@RequestParam Long employee_id){
        return reportService.getAllByEmployeeId(employee_id);
    }
}

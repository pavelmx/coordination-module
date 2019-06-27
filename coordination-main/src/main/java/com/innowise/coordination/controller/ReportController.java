package com.innowise.coordination.controller;

import com.innowise.coordination.entity.Report;
import com.innowise.coordination.filter.ReportFilter;
import com.innowise.coordination.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/filter")
    public ResponseEntity<List<Report>> getAllWithFilter(@RequestBody ReportFilter filter) {
        return ResponseEntity.ok(reportService.getAllWithFilter(filter));
    }

}

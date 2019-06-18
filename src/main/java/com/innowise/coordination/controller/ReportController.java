package com.innowise.coordination.controller;

import com.innowise.coordination.entity.Report;
import com.innowise.coordination.service.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController extends AbstractController<Report, ReportService>{

    protected ReportController(ReportService service) {
        super(service);
    }
}

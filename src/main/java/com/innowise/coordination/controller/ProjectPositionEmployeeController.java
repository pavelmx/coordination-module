package com.innowise.coordination.controller;

import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.service.ProjectPositionEmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project-position-employee")
public class ProjectPositionEmployeeController extends AbstractController<ProjectPositionEmployee, ProjectPositionEmployeeService>{

    protected ProjectPositionEmployeeController(ProjectPositionEmployeeService service) {
        super(service);
    }
}

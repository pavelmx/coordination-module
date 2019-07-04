package com.innowise.coordination.controller;

import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.service.ProjectPositionEmployeeService;
import com.innowise.coordination.service.ProjectPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project-position-employee")
public class ProjectPositionEmployeeController extends AbstractController<ProjectPositionEmployee, ProjectPositionEmployeeService>{

    @Autowired
    private ProjectPositionEmployeeService service;

    protected ProjectPositionEmployeeController(ProjectPositionEmployeeService service) {
        super(service);
    }

    @GetMapping("/project")
    public ResponseEntity<?> getEmployeesByProject(@RequestParam Long id_project) {
        return ResponseEntity.ok(service.getEmployeesByProject(id_project));
    }
}

package com.innowise.coordination.controller;

import com.innowise.coordination.entity.Customer;
import com.innowise.coordination.entity.Project;
import com.innowise.coordination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController extends AbstractController<Project, ProjectService>{

    @Autowired
    private ProjectService service;

    protected ProjectController(ProjectService service) {
        super(service);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Project>> getAllByCustomerId(@RequestParam Long id) {
        return ResponseEntity.ok(service.getAllByCustomerId(id));
    }

}

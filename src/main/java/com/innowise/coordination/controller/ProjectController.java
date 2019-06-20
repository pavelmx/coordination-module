package com.innowise.coordination.controller;

import com.innowise.coordination.entity.Project;
import com.innowise.coordination.entity.Response;
import com.innowise.coordination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/start")
    public ResponseEntity<Response> setStartProjectById(@RequestParam Long id) {
        return ResponseEntity.ok(new Response(service.setStartProjectById(id)));
    }

    @GetMapping("/end")
    public ResponseEntity<Response> setEndProjectById(@RequestParam Long id) {
        return ResponseEntity.ok(new Response(service.setEndProjectById(id)));
    }

}

package com.innowise.coordination.controller;

import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.entity.Response;
import com.innowise.coordination.service.ProjectPositionEmployeeService;
import com.innowise.coordination.service.ProjectPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project-position")
public class ProjectPositionController extends AbstractController<ProjectPosition, ProjectPositionService>{

    @Autowired
    private ProjectPositionService service;

    @Autowired
    private ProjectPositionEmployeeService projectPositionEmployeeService;

    protected ProjectPositionController(ProjectPositionService service) {
        super(service);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<ProjectPosition>> getAllByEmployeeId(@RequestParam Long id) {
        return ResponseEntity.ok(service.getAllByEmployeeId(id));
    }

    @GetMapping("/start")
    public ResponseEntity<Response> setStartWorkById(@RequestParam Long id) {
        return ResponseEntity.ok(new Response(service.setStartWorkById(id)));
    }

    @GetMapping("/end")
    public ResponseEntity<Response> setEndWorkById(@RequestParam Long id) {
        return ResponseEntity.ok(new Response(service.setEndWorkById(id)));
    }


}

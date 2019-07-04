package com.innowise.coordination.service;

import com.google.common.collect.Lists;
import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.entity.QProjectPosition;
import com.innowise.coordination.repository.ProjectPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectPositionService extends AbstractService<ProjectPosition, ProjectPositionRepository, QProjectPosition>{

    @Autowired
    private ProjectPositionRepository repository;

    @Autowired
    private ProjectPositionEmployeeService projectPositionEmployeeService;

    public ProjectPositionService(ProjectPositionRepository repository) {
        super(repository);
    }

    public List<ProjectPosition> getAllByEmployeeId(Long id) {
        return repository.findByProjectPositionEmployee_EmployeeId(id);
    }

    public List<ProjectPosition> getAllByProjectId(Long id) {
        return Lists.newArrayList(repository.findAll(QProjectPosition.projectPosition.project.id.eq(id)));
    }

    @Override
    public Optional<ProjectPosition> save(ProjectPosition entity) {
        ProjectPositionEmployee projectPositionEmployee =  projectPositionEmployeeService.save(entity.getProjectPositionEmployee()).get();
        entity.setProjectPositionEmployee(projectPositionEmployee);
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<ProjectPosition> update(ProjectPosition entity) {
        if(!repository.existsById(entity.getId())){
            throw new EntityNotFoundException("ProjectPosition with this id not found");
        }
        return super.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("ProjectPosition with this id not found");
        }
        super.deleteById(id);
    }

    public String setStartWorkById(Long id) {
        ProjectPosition projectPosition = get(id).get();
        LocalDate startDate = LocalDate.now();
        projectPosition.setStartDate(startDate);
        repository.save(projectPosition);
        String response = "You start work on project '" + projectPosition.getProject().getName() +
                "' at position '" + projectPosition.getProjectPositionEmployee().getPosition() + "'";
        return response;
    }

    public String setEndWorkById(Long id) {
        ProjectPosition projectPosition = get(id).get();
        LocalDate endDate = LocalDate.now();
        projectPosition.setEndDate(endDate);
        repository.save(projectPosition);
        String response = "You finish work on project '" + projectPosition.getProject().getName() +
                "' at position '" + projectPosition.getProjectPositionEmployee().getPosition() + "'";
        return response;
    }

    public Page<ProjectPosition> getPageByEmployeeId(Long id, int page, int size, String order, String column) {
        Pageable pageable;
        if (order.equals("")) {
            pageable = PageRequest.of(page, size);
        } else {
            pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        }
        return repository.findByProjectPositionEmployee_EmployeeId(id, pageable);
    }
}

package com.innowise.coordination.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table
public class ProjectPosition extends AbstractEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", schema = "coordination_schema", sequenceName = "sq_project_position")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "project_position_employee_id")
    private ProjectPositionEmployee projectPositionEmployee;

    private LocalDate plannedStartDate;

    private LocalDate plannedEndDate;

    private LocalDate startDate;

    private LocalDate endDate;
}

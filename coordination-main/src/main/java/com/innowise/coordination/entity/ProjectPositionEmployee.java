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
public class ProjectPositionEmployee extends AbstractEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", schema = "coordination_schema", sequenceName = "sq_project_position_employee")
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    private String position;

    private Float rate;
}

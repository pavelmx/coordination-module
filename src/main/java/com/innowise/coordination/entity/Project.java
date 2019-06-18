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
@Table(name = "project")
public class Project extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", schema = "coordination_schema", sequenceName = "sq_project")
    private Long id;

    private String name;

    private String code;

    private String model;

    private LocalDate plannedStartDate;

    private LocalDate plannedEndDate;

    private LocalDate startDate;

    private LocalDate endDate;

    private int hoursForProject;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

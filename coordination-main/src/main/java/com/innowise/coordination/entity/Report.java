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
public class Report extends AbstractEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", schema = "coordination_schema", sequenceName = "sq_report")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private Long employeeId;

    private String task;

    private String descriptionTask;

    private Float hoursForTask;

    private LocalDate reportDate;

    private ReportType reportType;
}

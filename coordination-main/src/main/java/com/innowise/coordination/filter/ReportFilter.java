package com.innowise.coordination.filter;

import lombok.Data;

@Data
public class ReportFilter {
    private String descriptionTask;
    private String employeeId;
    private String firstDate;
    private String hoursForTask;
    private String lastDate;
    private String task;
    private String reportType;
    private String projectId;
    private String showFor;
    private String month;
}

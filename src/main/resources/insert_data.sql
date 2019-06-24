insert into coordination_schema.customer (id, name, contact, code) values (1, 'Customer1', 'contact of customer1', '#4843') on conflict do nothing;
insert into coordination_schema.customer (id, name, contact, code) values (2, 'Customer2', 'contact of customer2', '#1') on conflict do nothing;
insert into coordination_schema.customer (id, name, contact, code) values (3, 'Customer3', 'contact of customer3', '#567') on conflict do nothing;

insert into coordination_schema.project (id, name, model, planned_start_date, planned_end_date, start_date, end_date, hours_for_project, customer_id, code)
values (1, 'Project1', 'Waterfall', '2019-05-05', '2020-05-05', '2019-05-05', null, 8600, 2, '#sec') on conflict do nothing;
insert into coordination_schema.project (id, name, model, planned_start_date, planned_end_date, start_date, end_date, hours_for_project, customer_id, code)
values (2, 'Project2', 'Scrum', '2019-05-25', '2019-12-25', '2019-05-26', null, 5000, 3, '#terro') on conflict do nothing;

insert into coordination_schema.project_position_employee (id, employee_id, position, rate) values (1, 1, 'QA backend', 0.5) on conflict do nothing;
insert into coordination_schema.project_position_employee (id, employee_id, position, rate) values (2, 1, 'QA front-end', 0.5) on conflict do nothing;

insert into coordination_schema.project_position (id, project_id, project_position_employee_id, planned_start_date, planned_end_date, start_date, end_date)
values (1, 1, 1, '2019-05-29', '2019-06-29', '2019-05-29', null) on conflict do nothing;
insert into coordination_schema.project_position (id, project_id, project_position_employee_id, planned_start_date, planned_end_date, start_date, end_date)
values (2, 1, 2, '2019-06-30', '2019-11-01', null, null) on conflict do nothing;

insert into coordination_schema.report (id, project_id, employee_id, hours_for_task, description_task, task, report_date, report_type)
values (1, 1, 1, 5, null, 'develop authorization', '2019-05-29', 3) on conflict do nothing;
insert into coordination_schema.report (id, project_id, employee_id, hours_for_task, description_task, task, report_date, report_type)
values (2, 1, 1, 3, 'boring', 'writing tests for authorization', '2019-05-29', 3) on conflict do nothing;
insert into coordination_schema.report (id, project_id, employee_id, hours_for_task, description_task, task, report_date, report_type)
values (3, 1, 1, 1, null, 'develop reset password', '2019-05-30', 3) on conflict do nothing;
insert into coordination_schema.report (id, project_id, employee_id, hours_for_task, description_task, task, report_date, report_type)
values (4, 1, 1, 2, null, 'develop sending mail to confirm account and change password', '2019-05-30', 3) on conflict do nothing;
insert into coordination_schema.report (id, project_id, employee_id, hours_for_task, description_task, task, report_date, report_type)
values (5, 1, 1, 2, null, 'develop ui for login and register', '2019-05-30', 3) on conflict do nothing;
insert into coordination_schema.report (id, project_id, employee_id, hours_for_task, description_task, task, report_date, report_type)
values (6, 1, 1, 3, 'boring', 'testing ui', '2019-05-30', 3) on conflict do nothing;
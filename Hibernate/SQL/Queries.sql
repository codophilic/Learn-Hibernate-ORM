select * from one_to_one_payroll;
select * from one_to_one_personal;

select * from student_data;
/**
Force to drop tables which consist 
foreign keys
**/
SET foreign_key_checks = 0;
-- Drop tables
drop table one_to_one_personal;
drop table one_to_one_payroll;
SET foreign_key_checks = 1;

select * from one_to_many_customer;
select * from many_to_one_customerorder;

SET foreign_key_checks = 0;
drop table many_to_many_employeeprojects;
drop table many_to_many_projects;
drop table many_to_many_employeeprojects_many_to_many_projects;
drop table many_to_many_projects_many_to_many_employeeprojects;
SET foreign_key_checks = 1;

select * from many_to_many_employeeprojects;
select * from many_to_many_projects;
select * from joined_table_employee_project;


select *  from one_to_one_personal;
select * from one_to_many_customer;
select * from many_to_one_customerorder;


select * from employee;

select count(*) from paginator;



select * from one_to_one_payroll;
select * from one_to_one_personal;

drop table one_to_one_personal;
drop table one_to_one_payroll;

/**
Force to drop tables which consist 
foreign keys
**/
SET foreign_key_checks = 0;
-- Drop tables
drop table one_to_one_personal;
drop table one_to_one_payroll;
SET foreign_key_checks = 1;


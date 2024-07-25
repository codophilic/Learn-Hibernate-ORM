# ORM (Object Relational Mapping) OR O/R mapping
- Lets say we have an Employee Management System. We want to manager employee information.
- Each employee has details like employeeID, name, department, and salary. You want to perform operations such as adding new employees, updating their information, retrieving details, and deleting records when employees leave.

#### Without using ORM technique
- Without using ORM, you would need to write SQL queries for each operation. For instance:
  - To add a new employee, you'd write an INSERT SQL query.
  - To update an employee's salary, you'd write an UPDATE SQL query.
  - To fetch employee details, you'd write a SELECT SQL query.
- Each operation requires careful handling of SQL syntax, data types, and potential errors.

#### With ORM
- What if the columns of a table has some mapping with objects in programming? basically could there be a class of employees as objects in your programming language. Let's say you have an Employee class with attributes like employeeID, name, department, and salary.
- So instead of writting SQL queries, we would have store the value in the object and pass that object to perform database operation.
- So thats what ORM handles this.

## What is ORM?
- Object-Relational Mapping, is a technique used in programming to map objects in your code to tables in a relational database. This mapping allows developers to interact with the database using the same object-oriented paradigms they use in their code, rather than writing raw SQL queries.

![image](https://github.com/user-attachments/assets/eeec2955-558c-4c52-bcde-e3ea4c92b7ec)

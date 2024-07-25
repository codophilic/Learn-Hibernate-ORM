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

## Why ORM?
1. **Abstraction:** ORM provides a layer of abstraction over the database, which makes it easier to work with the database using object-oriented principles. You don't have to write complex SQL queries; instead, you can work with objects and methods.
2. **Convenience:** It simplifies database operations like insert, update, delete, and query by allowing you to use your programming language's syntax and features, such as classes and objects.
3. **Code Readability:** Using ORM makes the code more readable and maintainable. You define your data structure and relationships in the code, and the ORM handles the rest.
4. **Portability:** It can make it easier to switch between different types of databases (e.g., MySQL, PostgreSQL) because the ORM abstracts the specifics of each database.

- ORM (Object-Relational Mapping) techniques can be integrated with many programming languages. ORM is a general approach to bridge the gap between object-oriented programming languages and relational databases, allowing developers to work with data using objects rather than raw SQL queries.
- In the Java ecosystem, several popular ORM tools and frameworks leverage the ORM technique to facilitate database interactions. These tools are **Hibernate,JPA (Java Persistence API), TopLink and many more.**

## Hibernate
- Hibernate is a Java framework that simplifies the development of Java application to interact with the database. It is an open source, lightweight, ORM (Object Relational Mapping) tool.
- By mapping Java objects to database tables, it streamlines data persistence and retrieval without the need for complex SQL queries.
- 

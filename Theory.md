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
- **ORMs have been primarily designed for relational databases (RDBMS). The concept of mapping objects to relational tables has been the core focus. This is why ORMs like Hibernate, SQLAlchemy, and Entity Framework have flourished in this domain.**

## Hibernate
- Hibernate is a Java framework that simplifies the development of Java application to interact with the database. It is an open source, lightweight, ORM (Object Relational Mapping) tool.
- By mapping Java objects to database tables, it streamlines data persistence and retrieval without the need for complex SQL queries.
- **Hibernate primarily works with Relational Database Management Systems (RDBMS). It's designed to bridge the gap between object-oriented programming languages (like Java) and relational databases.**
- Hibernate is a non-invasive framework, it means hibernate does not enforced developers to extends/implement any class or interfaces. It works only with existing class.
- Now lets use hibernate and mysql. For that we need to have its dependencies in our maven pom.xml project.

```
    <!-- https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.5.2.Final</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>
```

- Post dependencies are downloaded we need to define hibernation configurations. This can be done using **cfg.xml** file.
- Below we have defined hibernate cfg file. Here we are using MySQL database.

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration SYSTEM 
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- Version 8 MySQL hiberante-cfg.xml example for Hibernate 5 -->
<hibernate-configuration>
  <session-factory>
  <!-- Driver name -->
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <!-- property name="connection.driver_class">com.mysql.jdbc.Driver</property -->
    <property name="connection.url">jdbc:mysql://localhost:3306/myhibernatedb</property>
    <!-- 
    a "dialect" is a configuration setting that specifies the type of database you are using. 
    It tells Hibernate how to generate the appropriate SQL statements for your particular database system,
     as different databases have different SQL syntax, data types, and functions.
     -->
    <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="connection.username">root</property>
    <property name="connection.password">Meetpandya40@</property>
<!--     <property name="connection.pool_size">3</property>
 -->    <!--property name="dialect">org.hibernate.dialect.MySQLDialect</property-->
<!--     <property name="current_session_context_class">thread</property>
 -->    
 	<!-- 
 	show sql =  true states that whatever hibernate fires the query it will show in the console.
 	 -->
    <property name="show_sql">true</property>
    <property name="format_sql">true</property>
    
    <!-- 
    When we use create , it will create table , but if existing tables are there those will get deleted
    and again will get created. So it is better to use update over create. It will create only once if
    table does not exists.
     -->
    <property name="hbm2ddl.auto">update</property>
    <!-- mapping class="com.mcnz.jpa.examples.Player" / -->
  </session-factory>
</hibernate-configuration>
```

- Now using hibernate cfg file , lets test that we have properly configured it.

```
package orm.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainMethod {
  public static void main(String[] args) {

	  /**
	   * This line creates a new instance of the Configuration class from Hibernate.
	   * The Configuration object is used to configure Hibernate and set up its properties.
	   */
	  Configuration con=new Configuration();
	  
	  /**
	   * This line tells the Configuration object to load the configuration settings from the file
	   *  hibernateConfig.cfg.xml, located in the orm/hibernate directory.
	   * The XML file contains important settings such as database connection details, dialect, 
	   *  mappings, and other Hibernate configurations.
	   */
	  con.configure("orm/hibernate/hibernateConfig.cfg.xml");
  
	  /**
	   * The SessionFactory is a crucial object in Hibernate. It is a factory for Session objects, 
	   * which are used to interact with the database. The SessionFactory is typically created once 
	   * and used to create multiple Session instances.
	   */
	  SessionFactory ssf=con.buildSessionFactory();
	  
	  System.out.println(ssf);
			  
  }
}

Output:
org.hibernate.internal.SessionFactoryImpl@17273273
```

- Lets create a Student class.

```
package orm.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Student_data")
public class Student {
	
	@Id
	private int id;
	
	private String name;
	private String city;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
	
	
	
}

```

- Here in this approach we have used annotation to define relation between object and database tables. The `@Entity` annotation is used to specify that a given class is an entity and is mapped to a database table. This tells the ORM framework (like Hibernate) that objects of this class should be persisted in the database.
- The `@Id` annotation is used to denote the primary key of an entity. The primary key is a unique identifier for each entity instance.
- `@Table` tells hibernate if there is no table with name **Student_data** then create it.
- Currently there are no tables under database **myhibernatedb**.


![alt text](Images/image.png)

- So we have defined our entity and create instance variables for it. Now we need to configure this entity into our cfg.xml file or map this entity into our cfg.xml file.

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration SYSTEM 
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- Version 8 MySQL hiberante-cfg.xml example for Hibernate 5 -->
<hibernate-configuration>
  <session-factory>
  <!-- Driver name -->
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <!-- property name="connection.driver_class">com.mysql.jdbc.Driver</property -->
    <property name="connection.url">jdbc:mysql://localhost:3306/myhibernatedb</property>
    <!-- 
    a "dialect" is a configuration setting that specifies the type of database you are using. 
    It tells Hibernate how to generate the appropriate SQL statements for your particular database system,
     as different databases have different SQL syntax, data types, and functions.
     -->
    <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="connection.username">root</property>
    <property name="connection.password">Meetpandya40@</property>
<!--     <property name="connection.pool_size">3</property>
 -->    <!--property name="dialect">org.hibernate.dialect.MySQLDialect</property-->
<!--     <property name="current_session_context_class">thread</property>
 -->    
 	<!-- 
 	show sql =  true states that whatever hibernate fires the query it will show in the console.
 	 -->
    <property name="show_sql">true</property>
    <property name="format_sql">true</property>
    
    <!-- 
    When we use create , it will create table , but if existing tables are there those will get deleted
    and again will get created. So it is better to use update over create. It will create only once if
    table does not exists.
     -->
    <property name="hbm2ddl.auto">update</property>
    <!-- mapping class="com.mcnz.jpa.examples.Player" / -->
    <mapping class="orm.hibernate.Student"/>
  </session-factory>
</hibernate-configuration>
```

-  Now lets create table and insert some values into it. So uptil we have create one defined hibernate configuration in a cfg.xml, loaded configuratin and SessionFactory for hibernate and created an entity. Now to perform any database operation using java we need to have a session. Now when we create session from there we need to derived a transaction, database operations works transaction.

```
package orm.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainMethod {
  public static void main(String[] args) {

	  /**
	   * This line creates a new instance of the Configuration class from Hibernate.
	   * The Configuration object is used to configure Hibernate and set up its properties.
	   */
	  Configuration con=new Configuration();
	  
	  /**
	   * This line tells the Configuration object to load the configuration settings from the file
	   *  hibernateConfig.cfg.xml, located in the orm/hibernate directory.
	   * The XML file contains important settings such as database connection details, dialect, 
	   *  mappings, and other Hibernate configurations.
	   */
	  con.configure("orm/hibernate/hibernateConfig.cfg.xml");
  
	  /**
	   * The SessionFactory is a crucial object in Hibernate. It is a factory for Session objects, 
	   * which are used to interact with the database. The SessionFactory is typically created once 
	   * and used to create multiple Session instances.
	   */
	  SessionFactory ssf=con.buildSessionFactory();
	  
	  Student st=new Student();
	  st.setId(1);
	  st.setName("Harsh");
	  st.setCity("Mumbai");
	  
	  /**
	   * Created a session
	   */
	  Session session=ssf.openSession();
	  
	  /**
	   * Using session we created a new transaction
	   */
	  Transaction tx=session.beginTransaction();
	  
	  /**
	   * Save or inserted student object
	   */
	  session.save(st);
	  
	  /**
	   * Committed the transaction.
	   */
	  tx.commit();
	  
	  /**
	   * Close the resources
	   */
	  
	  session.close();
	  
			  
  }
}

Output:
Hibernate: 
    create table Student_data (
        id integer not null,
        city varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=InnoDB
Hibernate: 
    insert 
    into
        Student_data
        (city, name, id) 
    values
        (?, ?, ?)
```

- Post execution of main method, we can see a new table is created and a value is insert.

![alt text](image1.png)

- Some commonly used annotations are listed below
1. `@Entity`: Marks the class as a Hibernate entity, mapping it to a database table.
2. `@Id`: Specifies the primary key of the entity.
3. `@GeneratedValue`: Configures the way of incrementing the specified column (in this case, the primary key).Here, GenerationType.IDENTITY uses the database's identity column feature.
4. `@Column`: Maps the field to a database column with options like name, nullable, unique, etc.
5. `@Transient`: Indicates that the field is not to be persisted in the database.
6. `@Temporal`: Specifies the date/time type (DATE, TIME, or TIMESTAMP) for a Date field in the database.
7. `@Lob`: Designates a field as a Large Object, suitable for large binary or text data.
8. `@Enumerated`: Maps an enum type to a database column. EnumType.STRING stores the enum name as a string.

- Let us try all above annotation by creating a class of Address. So lets say we want to create a address table which stores student address details. 

```
package orm.hibernate;

import java.util.Date;

import jakarta.persistence.*;

/**
 * By specify entity annotation with name , hibernate will create table (if it not exists) by
 * the provided name i.e student_address_data
 */
@Entity(name="student_address_data")
public class Address {

	/**
	 * Made this object as primary and
	 * kept auto incremental value for it
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int unique_identifier;
	
	/**
	 * Hibernate will create a column with name first_line_address instead of address1
	 * with max length of 50
	 */
	@Column(name="first_line_address",length = 50)
	private String address1;
	
	@Column(name="second_line_address",length = 50)
	private String address2;
	
	/**
	 * Stores complete timestamp details
	 * in the column name details_creation_date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="details_creation_date")
	private Date stores_timestamp;
	
	/**
	 * Since we are storing timestamp, we don't required 
	 * to store the date again, these parameter may be used
	 * for any other computation purpose
	 */
	@Transient
	private Date stores_date;
	
	/**
	 * Stores student photo
	 */
	@Column(name="student_photo")
	@Lob
	private byte[] photo;
	
	/**
	 * To store Enum types of variables
	 */
    @Enumerated(EnumType.STRING)
    @Column(name="form_status")
	private String form;
    
    public enum String{
    	PENDING,
    	SUBMITED;
    }

	public int getUnique_identifier() {
		return unique_identifier;
	}

	public void setUnique_identifier(int unique_identifier) {
		this.unique_identifier = unique_identifier;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getStores_timestamp() {
		return stores_timestamp;
	}

	public void setStores_timestamp(Date stores_timestamp) {
		this.stores_timestamp = stores_timestamp;
	}

	public Date getStores_date() {
		return stores_date;
	}

	public void setStores_date(Date stores_date) {
		this.stores_date = stores_date;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
  
	@Override
	public String toString() {
		return "Address [unique_identifier=" + unique_identifier + ", address1=" + address1 + ", address2=" + address2
				+ ", stores_timestamp=" + stores_timestamp + ", stores_date=" + stores_date + ", photo="
				+ Arrays.toString(photo) + ", form=" + form + "]";
	}
}
```
- We have created our entity now we need to defined that this entity should be considered by hibernate .

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration SYSTEM 
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- Version 8 MySQL hiberante-cfg.xml example for Hibernate 5 -->
<hibernate-configuration>
  <session-factory>
  <!-- Driver name -->
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <!-- property name="connection.driver_class">com.mysql.jdbc.Driver</property -->
    <property name="connection.url">jdbc:mysql://localhost:3306/myhibernatedb</property>
    <!-- 
    a "dialect" is a configuration setting that specifies the type of database you are using. 
    It tells Hibernate how to generate the appropriate SQL statements for your particular database system,
     as different databases have different SQL syntax, data types, and functions.
     -->
    <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="connection.username">root</property>
    <property name="connection.password">Meetpandya40@</property>
<!--     <property name="connection.pool_size">3</property>
 -->    <!--property name="dialect">org.hibernate.dialect.MySQLDialect</property-->
<!--     <property name="current_session_context_class">thread</property>
 -->    
 	<!-- 
 	show sql =  true states that whatever hibernate fires the query it will show in the console.
 	 -->
    <property name="show_sql">true</property>
    <property name="format_sql">true</property>
    
    <!-- 
    When we use create , it will create table , but if existing tables are there those will get deleted
    and again will get created. So it is better to use update over create. It will create only once if
    table does not exists.
     -->
    <property name="hbm2ddl.auto">update</property>
    <!-- mapping class="com.mcnz.jpa.examples.Player" / -->
    <mapping class="orm.hibernate.annotation.Student"/>
    <mapping class="orm.hibernate.annotation.Address"/>
  </session-factory>
</hibernate-configuration>
```

- Post running our main method , we can see table is created and data is inserted.

```
package orm.hibernate.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import orm.hibernate.annotation.Address.formStatus;

public class MainMethod {
  public static void main(String[] args) {

	  /**
	   * This line creates a new instance of the Configuration class from Hibernate.
	   * The Configuration object is used to configure Hibernate and set up its properties.
	   */
	  Configuration con=new Configuration();
	  
	  /**
	   * This line tells the Configuration object to load the configuration settings from the file
	   *  hibernateConfig.cfg.xml, located in the orm/hibernate directory.
	   * The XML file contains important settings such as database connection details, dialect, 
	   *  mappings, and other Hibernate configurations.
	   */
	  con.configure("orm/hibernate/annotation/hibernateConfig.cfg.xml");
  
	  /**
	   * The SessionFactory is a crucial object in Hibernate. It is a factory for Session objects, 
	   * which are used to interact with the database. The SessionFactory is typically created once 
	   * and used to create multiple Session instances.
	   */
	  SessionFactory ssf=con.buildSessionFactory();
	  
	  /**
	   * Created a session
	   */
	  Session session=ssf.openSession();
	  
	  /**
	   * Using session we created a new transaction
	   */
	  Transaction tx=session.beginTransaction();
	  
	  Address address=new Address();
	  address.setAddress1("abc");
	  address.setAddress2("xyz");
	  address.setForm(formStatus.PENDING);
	  address.setStores_date(new Date());
	  address.setStores_timestamp(new Date());
	  try {
		FileInputStream fis=new FileInputStream("src/main/java/orm/hibernate/annotation/image.png");
		try {
			byte[] image=new byte[fis.available()];
			address.setPhoto(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  session.save(address);
	  /**
	   * Committed the transaction.
	   */
	  tx.commit();
	  
	  /**
	   * Close the resources
	   */
	  
	  session.close();
			  
  }
}


Output:
Hibernate: 
    create table student_address_data (
        unique_identifier integer not null auto_increment,
        first_line_address varchar(50),
        second_line_address varchar(50),
        form_status enum ('PENDING','SUBMITED'),
        student_photo mediumblob,
        details_creation_date datetime(6),
        primary key (unique_identifier)
    ) engine=InnoDB
Hibernate: 
    insert 
    into
        student_address_data
        (first_line_address, second_line_address, form_status, student_photo, details_creation_date) 
    values
        (?, ?, ?, ?, ?)
```

![alt text](image-1.png)

- Now lets say we want to get rows or load rows, how to do it?, hibernate provides **get()** and **load()** method both. 

```
package orm.hibernate.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import orm.hibernate.annotation.Address.formStatus;

public class MainMethod {
  public static void main(String[] args) {

	  /**
	   * This line creates a new instance of the Configuration class from Hibernate.
	   * The Configuration object is used to configure Hibernate and set up its properties.
	   */
	  Configuration con=new Configuration();
	  
	  /**
	   * This line tells the Configuration object to load the configuration settings from the file
	   *  hibernateConfig.cfg.xml, located in the orm/hibernate directory.
	   * The XML file contains important settings such as database connection details, dialect, 
	   *  mappings, and other Hibernate configurations.
	   */
	  con.configure("orm/hibernate/annotation/hibernateConfig.cfg.xml");
  
	  /**
	   * The SessionFactory is a crucial object in Hibernate. It is a factory for Session objects, 
	   * which are used to interact with the database. The SessionFactory is typically created once 
	   * and used to create multiple Session instances.
	   */
	  SessionFactory ssf=con.buildSessionFactory();
	  
	  /**
	   * Created a session
	   */
	  Session session=ssf.openSession();

	  /**
	   * Using Get Method
	   */
	  Address ad=session.get(Address.class, 1);
	  System.out.println(ad.getAddress1()+" "+ad.getAddress2());
	  /**
	   * Using Load Method
	   */
	  Address ad1=session.load(Address.class, 1);
	  System.out.println(ad1.getAddress1()+" "+ad1.getAddress2());
	  
	  /**
	   * Close the resources
	   */
	  
	  session.close();
			  
  }
}


Output:
Hibernate: 
    alter table student_address_data 
       modify column student_photo mediumblob
Hibernate: 
    select
        a1_0.unique_identifier,
        a1_0.first_line_address,
        a1_0.second_line_address,
        a1_0.form_status,
        a1_0.student_photo,
        a1_0.details_creation_date 
    from
        student_address_data a1_0 
    where
        a1_0.unique_identifier=?
abc xyz
abc xyz
```

- Post execution of above method, we are able to retrieve data. If you observe, both get and load method provides the data, then why there are two different methods?
- In Hibernate, you can retrieve rows (or entities) from the database using the Session interface's load and get methods. These methods are used to fetch an entity based on its primary key. While both serve similar purposes, they have some differences in behavior:


| Feature             | `load`                              | `get`                               |
|---------------------|-------------------------------------|-------------------------------------|
| Return Type         | Proxy or entity                     | Actual entity or `null`             |
| Database Hit        | Deferred until a property is accessed | Immediate                           |
| Exception on Not Found | Throws `ObjectNotFoundException` | Returns `null`                      |
| Use Case            | When the entity is expected to exist | When the entity may or may not exist |
| Performance         | May improve performance by delaying the database hit | involves database hit if object does not exists in Session cache and return a fully object which may include several database call  |


- In java, one class may be dependent on another class. So in such case we need to embedded class. The `@Embedded` annotation in Hibernate is used to include an embeddable class (`@Embeddable`) into another entity. In simple terms, it means that the fields of the embedded class are treated as if they were fields of the containing entity itself, but they are logically grouped together.
- So lets say we have an Employee class.

```
package orm.hibernate.annotation;

import jakarta.persistence.*;

@Entity
public class Employee {

	private String empid;
	private String deptname;

	@Embedded
	private EmployeeAddress empAddr;
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public EmployeeAddress getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(EmployeeAddress empAddr) {
		this.empAddr = empAddr;
	}
	
	
}
```

- Here the Employee class is dependent on EmployeeAddress class. So we need to embedded EmployeeAddress class with Employee class.

```
package orm.hibernate.annotation;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeAddress {

	private String address1;
	private String address2;
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	
}
```

- Below is the hibernate configuration xml file.

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration SYSTEM 
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- Version 8 MySQL hiberante-cfg.xml example for Hibernate 5 -->
<hibernate-configuration>
  <session-factory>
  <!-- Driver name -->
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <!-- property name="connection.driver_class">com.mysql.jdbc.Driver</property -->
    <property name="connection.url">jdbc:mysql://localhost:3306/myhibernatedb</property>
    <!-- 
    a "dialect" is a configuration setting that specifies the type of database you are using. 
    It tells Hibernate how to generate the appropriate SQL statements for your particular database system,
     as different databases have different SQL syntax, data types, and functions.
     -->
    <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="connection.username">root</property>
    <property name="connection.password">Meetpandya40@</property>
<!--     <property name="connection.pool_size">3</property>
 -->    <!--property name="dialect">org.hibernate.dialect.MySQLDialect</property-->
<!--     <property name="current_session_context_class">thread</property>
 -->    
 	<!-- 
 	show sql =  true states that whatever hibernate fires the query it will show in the console.
 	 -->
    <property name="show_sql">true</property>
    <property name="format_sql">true</property>
    
    <!-- 
    When we use create , it will create table , but if existing tables are there those will get deleted
    and again will get created. So it is better to use update over create. It will create only once if
    table does not exists.
     -->
    <property name="hbm2ddl.auto">create</property>
    <!-- mapping class="com.mcnz.jpa.examples.Player" / -->
    <mapping class="orm.hibernate.annotation.Student"/>
    <mapping class="orm.hibernate.annotation.Address"/>
    <mapping class="orm.hibernate.annotation.Employee"/>
  </session-factory>
</hibernate-configuration>
```

- Post execution of main method, we create table with name employee which includes object columns of employee as well as employeeaddress in a single table employee.

```
package orm.hibernate.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import orm.hibernate.annotation.Address.formStatus;

public class MainMethod {
  public static void main(String[] args) {

	  /**
	   * This line creates a new instance of the Configuration class from Hibernate.
	   * The Configuration object is used to configure Hibernate and set up its properties.
	   */
	  Configuration con=new Configuration();
	  
	  /**
	   * This line tells the Configuration object to load the configuration settings from the file
	   *  hibernateConfig.cfg.xml, located in the orm/hibernate directory.
	   * The XML file contains important settings such as database connection details, dialect, 
	   *  mappings, and other Hibernate configurations.
	   */
	  con.configure("orm/hibernate/annotation/hibernateConfig.cfg.xml");
  
	  /**
	   * The SessionFactory is a crucial object in Hibernate. It is a factory for Session objects, 
	   * which are used to interact with the database. The SessionFactory is typically created once 
	   * and used to create multiple Session instances.
	   */
	  SessionFactory ssf=con.buildSessionFactory();
	  
	  /**
	   * Created a session
	   */
	  Session session=ssf.openSession();
	  
	  /**
	   * Using session we created a new transaction
	   */
	  Transaction tx=session.beginTransaction();

	  Employee emp=new Employee();
	  emp.setEmpid("1");
	  emp.setDeptname("mumbai");
	  
	  EmployeeAddress empadd=new EmployeeAddress();
	  empadd.setAddress1("abc");
	  empadd.setAddress2("xyz");
	  emp.setEmpAddr(empadd);
	  System.out.println(emp.getEmpAddr().getAddress1());
	  session.save(emp);
	  
	  tx.commit();
	  
	  /**
	   * Close the resources
	   */
	  
	  session.close();
	  
	  
			  
  }
}

Output:
Hibernate: 
    create table Employee (
        address1 varchar(255),
        address2 varchar(255),
        deptname varchar(255),
        empid varchar(255) not null,
        primary key (empid)
    ) engine=InnoDB
Hibernate: 
    insert 
    into
        Employee
        (deptname, address1, address2, empid) 
    values
        (?, ?, ?, ?)
```
![alt text](image-2.png)

- Here upto we have seen all operations for a single table, lets say when we want to work with multiple tables which has relationship with each other, how shall we use hibernate over there?
- There are three types of relationships between the data you are likely to encounter at this stage in the design: one-to-one, one-to-many, and many-to-many.

#### One-to-One
- A one-to-one (1:1) relationship means that each record in Table A relates to one, and only one, record in Table B, and each record in Table B relates to one, and only one, record in Table A. Look at the following example of tables from a company's Employees database:

![alt text](image-3.png)

- So EmployeeID of Personal table is primary key which acts like a foreign key for Payroll table which established relationship between these two tables.
- In hibernate, we have `@OneToOne` annotation to achieve this. Lets say we have a class of Personal and Payroll.
- Here both classes are entity.

```
package orm.hibernate.annotation.onetoone;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="one_to_one_personal")
public class Personal {

	private int empid;
	
	private String firstname;
	
	private String lastname;
	
	@OneToOne
	private Payroll payroll;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}
	
	
	
}



package orm.hibernate.annotation.onetoone;

import jakarta.persistence.*;

@Entity
@Table(name="one_to_one_payroll")
public class Payroll {

	private int empid;
	
	private double payrate;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public double getPayrate() {
		return payrate;
	}

	public void setPayrate(double payrate) {
		this.payrate = payrate;
	}
	
	
}
```

- Below is the hibernate config xml file.

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration SYSTEM 
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- Version 8 MySQL hiberante-cfg.xml example for Hibernate 5 -->
<hibernate-configuration>
  <session-factory>
  <!-- Driver name -->
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <!-- property name="connection.driver_class">com.mysql.jdbc.Driver</property -->
    <property name="connection.url">jdbc:mysql://localhost:3306/myhibernatedb</property>
    <!-- 
    a "dialect" is a configuration setting that specifies the type of database you are using. 
    It tells Hibernate how to generate the appropriate SQL statements for your particular database system,
     as different databases have different SQL syntax, data types, and functions.
     -->
    <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="connection.username">root</property>
    <property name="connection.password">Meetpandya40@</property>
<!--     <property name="connection.pool_size">3</property>
 -->    <!--property name="dialect">org.hibernate.dialect.MySQLDialect</property-->
<!--     <property name="current_session_context_class">thread</property>
 -->    
 	<!-- 
 	show sql =  true states that whatever hibernate fires the query it will show in the console.
 	 -->
    <property name="show_sql">true</property>
    <property name="format_sql">true</property>
    
    <!-- 
    When we use create , it will create table , but if existing tables are there those will get deleted
    and again will get created. So it is better to use update over create. It will create only once if
    table does not exists.
     -->
    <property name="hbm2ddl.auto">create</property>
    <!-- mapping class="com.mcnz.jpa.examples.Player" / -->
<!--     <mapping class="orm.hibernate.annotation.Student"/>
    <mapping class="orm.hibernate.annotation.Address"/>
    <mapping class="orm.hibernate.annotation.Employee"/> -->
    <mapping class="orm.hibernate.annotation.onetoone.Personal" />
    <mapping class="orm.hibernate.annotation.onetoone.Payroll" />
  </session-factory>
</hibernate-configuration>
```

- Post execution main method, we can see the query log on the console.

```
package orm.hibernate.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import orm.hibernate.annotation.Address.formStatus;
import orm.hibernate.annotation.onetoone.Payroll;
import orm.hibernate.annotation.onetoone.Personal;

public class MainMethod {
  public static void main(String[] args) {

	  /**
	   * This line creates a new instance of the Configuration class from Hibernate.
	   * The Configuration object is used to configure Hibernate and set up its properties.
	   */
	  Configuration con=new Configuration();
	  
	  /**
	   * This line tells the Configuration object to load the configuration settings from the file
	   *  hibernateConfig.cfg.xml, located in the orm/hibernate directory.
	   * The XML file contains important settings such as database connection details, dialect, 
	   *  mappings, and other Hibernate configurations.
	   */
	  con.configure("orm/hibernate/annotation/hibernateConfig.cfg.xml");
  
	  /**
	   * The SessionFactory is a crucial object in Hibernate. It is a factory for Session objects, 
	   * which are used to interact with the database. The SessionFactory is typically created once 
	   * and used to create multiple Session instances.
	   */
	  SessionFactory ssf=con.buildSessionFactory();
	  
	  /**
	   * Created a session
	   */
	  Session session=ssf.openSession();
	  
	  /**
	   * Using session we created a new transaction
	   */
	  Transaction tx=session.beginTransaction();
	  
	  
	  Student st=new Student();
	  st.setId(1);
	  st.setName("Harsh");
	  st.setCity("Mumbai");
	  
	  /**
	   * Save or inserted student object
	   */
	  session.save(st);

	  
	  Address address=new Address();
	  address.setAddress1("abc");
	  address.setAddress2("xyz");
	  address.setForm(formStatus.PENDING);
	  address.setStores_date(new Date());
	  address.setStores_timestamp(new Date());
	  try {
		FileInputStream fis=new FileInputStream("src/main/java/orm/hibernate/annotation/image.png");
		try {
			byte[] image=new byte[fis.available()];
			address.setPhoto(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  session.save(address);
	  
	  /**
	   * Using Get Method
	   */
	  Address ad=session.get(Address.class, 1);
	  System.out.println(ad.getAddress1()+" "+ad.getAddress2());

	  /**
	   * Using Load Method
	   */
	  Address ad1=session.load(Address.class, 1);
	  System.out.println(ad1.getAddress1()+" "+ad1.getAddress2());
	  
	  Employee emp=new Employee();
	  emp.setEmpid("1");
	  emp.setDeptname("mumbai");
	  
	  EmployeeAddress empadd=new EmployeeAddress();
	  empadd.setAddress1("abc");
	  empadd.setAddress2("xyz");
	  emp.setEmpAddr(empadd);
	  session.save(emp);
	  
	  Personal ps=new Personal();
	  ps.setEmpid(1);
	  ps.setFirstname("Harsh");
	  ps.setLastname("Pandya");
	  
	  Payroll py=new Payroll();
	  py.setEmpid(1);
	  py.setPayrate(1000);
	  ps.setPayroll(py);
	  
	  session.save(ps);
	  session.save(py);
	  
	  tx.commit();
	  
	  /**
	   * Close the resources
	   */
	  
	  session.close();
	  
	  
			  
  }
}



Output:
Hibernate: 
    alter table one_to_one_personal 
       drop 
       foreign key FK47g9qrht6wxck2r11m9a55k2
Jul 26, 2024 12:18:47 AM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@353e6389] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 
    drop table if exists one_to_one_payroll
Hibernate: 
    drop table if exists one_to_one_personal
Hibernate: 
    create table one_to_one_payroll (
        empid integer not null,
        payrate float(53) not null,
        primary key (empid)
    ) engine=InnoDB
Jul 26, 2024 12:18:47 AM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@299b9851] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 
    create table one_to_one_personal (
        empid integer not null,
        payroll_empid integer,
        firstname varchar(255),
        lastname varchar(255),
        primary key (empid)
    ) engine=InnoDB
Hibernate: 
    alter table one_to_one_personal 
       add constraint UK4kkwyykxk478uksdive0f9cl8 unique (payroll_empid)
Hibernate: 
    alter table one_to_one_personal 
       add constraint FK47g9qrht6wxck2r11m9a55k2 
       foreign key (payroll_empid) 
       references one_to_one_payroll (empid)
Hibernate: 
    select
        null,
        p1_0.payrate 
    from
        one_to_one_payroll p1_0 
    where
        p1_0.empid=?
Hibernate: 
    insert 
    into
        one_to_one_personal
        (firstname, lastname, payroll_empid, empid) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        one_to_one_payroll
        (payrate, empid) 
    values
        (?, ?)
Hibernate: 
    update
        one_to_one_personal 
    set
        firstname=?,
        lastname=?,
        payroll_empid=? 
    where
        empid=?
```
- Since in the cfg xml file we have mentioned `<property name="hbm2ddl.auto">create</property>` , so hibernates drops the table and re-creates it. PS: earlier the table was created.
- Now when observe the query alter of table, we can see payroll_empid is foreign key in table one_to_one_personal.

```
    alter table one_to_one_personal 
       add constraint FK47g9qrht6wxck2r11m9a55k2 
       foreign key (payroll_empid) 
       references one_to_one_payroll (empid)
```

![alt text](image-5.png)

![alt text](image-4.png)

![alt text](image-6.png) 

#### One-to-Many
- A one-to-many (1:N) relationship means a record in Table A can relate to zero, one, or many records in Table B. Many records in Table B can relate to one record in Table A. 

![alt text](image-7.png)



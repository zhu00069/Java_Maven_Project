/**
* File: EmployeeDAO.java
* Author (original): Mike Norman, course materials (19W) CST 8277
*
* Modified Date: Feb 2019
* Author: I. M. Student 040-684-747
*
* Description: EmployeeDAO
* @authors Bo Zhu
*/
package com.algonquincollege.cst8277.daos;

import java.util.List;

import com.algonquincollege.cst8277.models.Employee;

public interface EmployeesDAO {
    
     //CRUD-Create
     public Employee addEmployee(Employee employee);
     
     //CRUD-Read
     public Employee getEmployee(int id);
     
     public List<Employee> getAllEmployees();
     
     //CRUD-Update
     public Employee updateEmployee(Employee employee);
     
     //CRUD-Delete
     public Employee deleteEmployee(Employee employee);
}

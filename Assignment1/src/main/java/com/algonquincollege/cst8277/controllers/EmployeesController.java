/**
* File: EmployeesController.java
* Author (original): Mike Norman, course materials (19W) CST 8277
*
* Modified Date: Feb 2019
* Author: I. M. Student 040-684-747
*
* Description: Employee Class Controller
* @authors Bo Zhu
*/
package com.algonquincollege.cst8277.controllers;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.algonquincollege.cst8277.daos.EmployeesDAO;
import com.algonquincollege.cst8277.models.Employee;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

@Named("employeesController")
@SessionScoped
public class EmployeesController implements Serializable {
    
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected List<Employee> employees;

    private EmployeesDAO employeesDAO;
    
 
    @Inject
    public EmployeesController(EmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
    
    /**
     * Get a list of all employees.
     * @return a list of employees.
     */
    public List<Employee> getListOfEmployees() {
        logger.debug("eror getting all list of employees");
        employees = employeesDAO.getAllEmployees();
        return employees;
    }
    
    /**
     * create/add an employee
     * @param employee add an new employee
     * @return url:list-employee.xhtml
     */
    public String addEmployee(Employee employee) {
        logger.debug("eror getting add an employee");
        employeesDAO.addEmployee(employee);
        return "list-contact.xhtml";
    }
    
    /**
     * update an employee
     * @param employee edit an exsit employee
     * @return url:list-contact.xhtml
     */
    public String updateEmployee(Employee employee) {
        logger.debug("error updating an employee");
        employeesDAO.updateEmployee(employee);
        return "list-contact.xhtml";
    }
    
    /**
     * delete an employee
     * @param employee delete an employee
     * @return url:list-contact.xhtml
     */
    public String deleteEmployee(Employee employee) {
        logger.debug("error deleting an employee");
        employeesDAO.deleteEmployee(employee);
        return "list-contact.xhtml";
    }
    


}

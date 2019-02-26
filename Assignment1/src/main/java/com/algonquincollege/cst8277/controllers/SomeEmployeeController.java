/**
* File: SomeEmployeeController.java
* Author (original): Mike Norman, course materials (19W) CST 8277
*
* Modified Date: Feb 2019
* Author: I. M. Student 040-684-747
*
* Description: SomeEmployeeController
* @authors Bo Zhu
*/
package com.algonquincollege.cst8277.controllers;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.algonquincollege.cst8277.daos.EmployeesDAO;
import com.algonquincollege.cst8277.models.Employee;

@Named("someEmployeeController")
@ApplicationScoped
public class SomeEmployeeController implements Serializable {

    private static final long serialVersionUID = 1L;

    private EmployeesDAO employeesDAO;

    @Inject
    public SomeEmployeeController(EmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;
    }
    
    /**
     * update an emloyee, get emloyee by id
     * @param id emloyee id of selected row 
     * @return to url: update-contact.xhtml
     */
    public String updateLoadEmployee(int id) {
        Employee e1 = employeesDAO.getEmployee(id);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("employee", e1);
        return "update-contact.xhtml";
    }

    /**
     * add an emloyee, create a new emloyee
     * @return to url: add-contact.xhtml
     */
    public String addEmployee() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("employee", new Employee());
        return "add-contact.xhtml";
    }
}
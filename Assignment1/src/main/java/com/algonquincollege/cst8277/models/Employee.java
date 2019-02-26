/**
* File: Employee.java
* Author (original): Mike Norman, course materials (19W) CST 8277
*
* Modified Date: Feb 2019
* Author: I. M. Student 040-684-747
*
* Description: Employee
* @authors Bo Zhu
*/
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ManagedBean
@ViewScoped
public class Employee implements Serializable{
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String title;
    
    /**
     * getter for id
     * @return id 
     */
    public int getId() {
        return id;
    }
    
    /**setter for employee id
     * @param id, id to set 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * getter for firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**setter for employee's firstName
     * @param firstName, firstName to set 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * getter for lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**setter for employee's lastName
     * @param lastName, lastName to set 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**getter for employee's titile
     * @return title
     */
    public String getTitle() {
        return title;
    }
    
    /**setter for employee's title
     * @param title, title  to set 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Employee [id=")
            .append(id)
            .append(", firstName=")
            .append(firstName)
            .append(", lastName=")
            .append(lastName)
            .append(", title=")
            .append(title)
            .append("]");
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

}

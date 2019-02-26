/**
 * File: EmployeesDAOImpl.java
 * Author (original): Mike Norman, course materials (19W) CST 8277
 *
 * Modified Date: Feb 2019
 * Author: I. M. Student 040-684-747
 *
 * Description: EmployeesDAOImpl
 * @authors Bo Zhu
 */
package com.algonquincollege.cst8277.daos;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;

import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.algonquincollege.cst8277.models.Employee;

@Named
@ApplicationScoped
public class EmployeesDAOImpl implements Serializable, EmployeesDAO {

    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String GET_ALL_EMPLOYEES = "SELECT * from EMPLOYEE ORDER BY id";
    private static final String GET_EMPLOYEE_BY_ID = "SELECT * from EMPLOYEE WHERE id=?";
    private static final String CREATE_EMPLOYEE = "INSERT INTO EMPLOYEE (firstname, lastname, title) VALUES (?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE EMPLOYEE SET firstname=?, lastname=?,title=? WHERE id =?";
    private static final String DELETE_EMPLOYEE = "DELETE from EMPLOYEE WHERE id=?";

    /*
     * Database connection and prepare to connect
     */

    @Resource(name = "jdbc/ContactsDb")
    protected DataSource contactsDb;

    Connection con = null;
    PreparedStatement pstmt = null;

    /**
     * CRUD-Create an employee
     * This method starts to connect database and prepare query, then create an employee
     * @param employee this is a new employee when add a new employee
     * @return new employee
     */
    @Override
    public Employee addEmployee(Employee employee) {

        try{
            con = contactsDb.getConnection();
            pstmt = con.prepareStatement(CREATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getTitle());
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if(generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                employee.setId(id);
            }
        } catch (SQLException ex) {
            logger.error("something went wrong creating Db", ex);
        }
        return employee;
    }

    /**
     * CRUD-read an employee by id
     * @param id this is the id of each employee
     * @return employee
     */
    @Override
    public Employee getEmployee(int id) {

        Employee employee = new Employee();

        ResultSet rs = null;

        try {
            con = contactsDb.getConnection();
            pstmt = con.prepareStatement(GET_EMPLOYEE_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("firstname"));
                employee.setLastName(rs.getString("lastname"));
                employee.setTitle(rs.getString("title"));
            }
        }
        catch (SQLException e) {
            logger.error("something went wrong getting employee by id = "+id, e);
        }finally{
            try {
                if (null != con) {
                    rs.close();
                    pstmt.close();
                    con.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        return employee;
    }

    /**
     * CRUD-read all employees(employee list)
     * @return employees
     */
    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<Employee>();

        ResultSet rs = null;

        try {
            con = contactsDb.getConnection();
            pstmt = con.prepareStatement(GET_ALL_EMPLOYEES);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("firstname"));
                employee.setLastName(rs.getString("lastname"));
                employee.setTitle(rs.getString("title"));
                employees.add(employee);

            }
        } catch (SQLException e) {
            logger.error("something went wrong getting all employees ");
        }finally{
            try {
                if (null != con) {
                    rs.close();
                    pstmt.close();
                    con.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        return employees;
    }

    /**
     * CRUD-update an employee
     * @param employee edit an exsit employee
     * @return employee
     */
    @Override
    public Employee updateEmployee(Employee employee) {

        try {
            con = contactsDb.getConnection();
            pstmt = con.prepareStatement(UPDATE_EMPLOYEE);
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getTitle());
            pstmt.setInt(4, employee.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("something went wrong updating employee:id="  + employee.getId());
        }finally{
            try {
                if (null != con) {
                    pstmt.close();
                    con.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }

        return employee;
    }

    /**
     * CRUD-delete an employee
     * @param employee delete an employee
     * @return employee
     */
    @Override
    public Employee deleteEmployee(Employee employee) {

        try {
            con = contactsDb.getConnection();
            pstmt = con.prepareStatement(DELETE_EMPLOYEE);
            pstmt.setInt(1, employee.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("something went wrong deleting employee:id=" + employee.getId());
        }finally{
            try {
                if (null != con) {
                    pstmt.close();
                    con.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }

        return employee;
    }

}





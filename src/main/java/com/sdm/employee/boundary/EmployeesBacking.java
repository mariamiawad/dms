package com.sdm.employee.boundary;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sdm.employee.controller.EmployeeDAO;
import com.sdm.employee.entity.Employee;

@Named
@ViewScoped
public class EmployeesBacking implements Serializable {

    private List<Employee> employees;

    private Employee newEmployee = new Employee();

    @Inject
    private EmployeeDAO employeeDAO;

    @PostConstruct
    public void init() {
        this.employees = employeeDAO.loadAllCEmployees();
    }

    public void delete(Employee employee) {
    	employeeDAO.delete(employee);
        employees.remove(employee);
    }

    public void add() {
    	employeeDAO.addNewEmployee(newEmployee);
        this.employees = employeeDAO.loadAllCEmployees();

//        this.newEmployee = new Employee(newEmployee.getName(), newEmployee.getEmail(), newEmployee.getAddress(),newEmployee.getEmployeeCode() );
    }

    public void update() {
    	employeeDAO.update(employees);
       
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

   

    public Employee getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(Employee newEmployee) {
		this.newEmployee = newEmployee;
	}

	public EmployeeDAO getEmployeeManager() {
        return employeeDAO;
    }

    public void setEmployeeManager(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}

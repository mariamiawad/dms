package com.sdm.employee.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sdm.employee.boundary.EmployeesBacking;
import com.sdm.employee.configurator.HibernateConnector;
import com.sdm.employee.entity.Employee;

@Stateless
public class EmployeeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Employee> loadAllCEmployees() {
//		return this.entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		HibernateConnector connector = HibernateConnector.getInstance();
		Session session = connector.getSession();
		List<Employee> employees;
		try {
//			session.beginTransaction();
			employees = (List<Employee>) session.createQuery("from Employee e").list();
			int count = employees.size();
			// FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "List
			// Size", Integer.toString(count));//Debugging Purpose
			// RequestContext.getCurrentInstance().showMessageInDialog(message1);
//			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
			return null;
		}
		session.close();
		return employees;
	}

	public void delete(Employee employee) {

		if (entityManager.contains(employee)) {
			entityManager.remove(employee);
		} else {
			Employee managedEmployee = entityManager.find(Employee.class, employee.getEmployeeId());
			if (managedEmployee != null) {
				entityManager.remove(managedEmployee);
			}
		}
		Session session = HibernateConnector.getInstance().getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(employee);
		transaction.commit();
		session.close();
	}

	public void addNewEmployee(Employee employee) {

		Session session = HibernateConnector.getInstance().getSession();

		List checkEmployee = session.createQuery("from Employee e where e.employeeCode =" + employee.getEmployeeCode())
				.getResultList();
		if (checkEmployee.isEmpty()) {
			session.save(employee);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duplicate Code", null));
		}

		session.close();
	}

	public void update(List<Employee> employees) {
		Session session = HibernateConnector.getInstance().getSession();
		
		
		boolean flag = true;
		for (Employee employee : employees) {
			try {
			Transaction transaction = session.beginTransaction();
				session.saveOrUpdate(employee);
				transaction.commit();
			} catch (Exception e) {
				flag = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can not update", null));
			}

		}
		if (flag) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Update success", null));
		}
		
		session.close();
	}
}
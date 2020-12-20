package com.sdm.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employeeId", updatable = false, nullable = false)
	private Long employeeId;

	@Column(nullable = false)
	@NotEmpty
	private String name;
	@Column(nullable = false)
	@NotEmpty
	private String address;

	@Column(nullable = false)
	@NotEmpty
	@Email
	private String email;

	@Column(nullable = false, unique = true)
	@NotEmpty
	private String employeeCode;

	public Employee() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + employeeId + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", employeeCode=" + employeeCode + "]";
	}

}

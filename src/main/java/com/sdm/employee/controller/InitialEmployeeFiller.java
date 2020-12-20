package com.sdm.employee.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sdm.employee.entity.Employee;

@Startup
@Singleton
public class InitialEmployeeFiller {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void init() {

	}

}
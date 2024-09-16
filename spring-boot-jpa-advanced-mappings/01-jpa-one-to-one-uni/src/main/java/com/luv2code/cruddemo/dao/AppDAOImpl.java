package com.luv2code.cruddemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.entity.Instructor;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{



	private EntityManager entityManager;
	
	@Autowired
	public AppDAOImpl(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	@Override
	@Transactional
	public void save(Instructor theInstructor)
	{
		entityManager.persist(theInstructor);
	}
	@Override
	public Instructor findInstructorById(int theId) {
		return entityManager.find(Instructor.class, theId);
	}
	@Override
	@Transactional
	public void deleteInstructorById(int theId) {
		Instructor tempInstructor = entityManager.find(Instructor.class, theId);
		
		entityManager.remove(tempInstructor);
		
	}
	
	
	
	
}

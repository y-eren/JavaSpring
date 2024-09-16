package com.luv2code.cruddemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

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
	
	@Override
	public InstructorDetail findInstructorDetailById(int theId) {
		return entityManager.find(InstructorDetail.class, theId);
	}
	@Override
	@Transactional
	public void deleteInstructorDetailById(int theId) {
InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
		
// cascade yaparken sadece bir tanesini silmek için böylelikle soldan sağa giden okun cascadei null olacaktır
tempInstructorDetail.getInstructor().setInstructorDetail(null);
		entityManager.remove(tempInstructorDetail);
		
	}
	
	
	
	
}

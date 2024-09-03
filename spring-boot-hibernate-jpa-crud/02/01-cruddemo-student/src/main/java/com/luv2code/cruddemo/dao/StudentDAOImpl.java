package com.luv2code.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

	// define field for entity manager
	private EntityManager entityManager; 
	
	// inject entity manager using constructor injection
	@Autowired
	public StudentDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	
	@Override
	@Transactional //updating database saving or deleting database
	public void save(Student theStudent) {
		entityManager.persist(theStudent);
		
	}



	@Override
	public Student findById(Integer id) {
		
		return entityManager.find(Student.class, id);
	}
	
	@Override
	public List<Student> findAll()
	{
		// create query 
		TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
		// return query results
		return theQuery.getResultList();
		
	}



	@Override
	public List<Student> findByLastName(String lastName) {
		// create query
		TypedQuery<Student>theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData ", Student.class);
		
		// setQueryResults
		theQuery.setParameter("theData", lastName);
		// return query results
		
		return theQuery.getResultList();
		
		
		
	}



	@Override
	@Transactional
	public void update(Student theStudent) {
		entityManager.merge(theStudent);
		
	}



	@Override
	@Transactional
	public void delete(Integer id) {
		// retrieve the student
		Student theStudent = entityManager.find(Student.class, id);
		
		// delete the student
		entityManager.remove(theStudent);
		
		
		
	}



	@Override
	@Transactional
	public int deleteAll() {
		int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate(); // sadece database i modifiye ederken kullanıldığı için 
		
		
		return 0;
	}
	
	


}

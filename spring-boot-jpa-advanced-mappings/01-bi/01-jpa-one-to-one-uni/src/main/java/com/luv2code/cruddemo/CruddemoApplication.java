package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	
	@Bean // bean olduğu için AppDAO'ya ihtiyaç duyulmamaktadır.
	public CommandLineRunner commandLineRunner(AppDAO appDAO)
	{
		return runner -> {
			// createInstructor(appDAO);
				//findInstructor(appDAO);
			//deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);

		};
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 1;
		appDAO.deleteInstructorDetailById(theId);
		
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 1;
		
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println(tempInstructorDetail);
		
		System.out.println("Instructor :" + tempInstructorDetail.getInstructor());
		
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		
		 appDAO.deleteInstructorById(theId);
		
		
	}

	private void findInstructor(AppDAO appDAO) {
		
		int theId = 1;
		
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		
		System.out.println(tempInstructor);
	}

	private void createInstructor(AppDAO appDAO) {
		
		// create the instructor
		
		Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@hotmail.com");
		
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://merhaba.com", "Love to code" );
		
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		
		// bu instructor detailide kaydedecektir çünkü cascade type all dan dolayı 
		System.out.println("Saving instructor" + tempInstructor);
		appDAO.save(tempInstructor);
	}

}

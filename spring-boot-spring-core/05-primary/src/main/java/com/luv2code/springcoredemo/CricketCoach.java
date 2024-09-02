package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component //bu annotation dependency injection için classı Spring bean için işaretler
public class CricketCoach implements Coach{

	@Override
	public String getDailyWorkout()
	{
		return "Practice fast bowling for 15 minutes";
	}
}

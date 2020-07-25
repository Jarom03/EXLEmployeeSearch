package com.schertz.assessment.models;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;

/**
 * @author Jarom Schertz An entity object representing an employee
 */
@Entity
public class Employee {

//	name, job title, age, start and end date of employment.
	private String name;
	private String jobTitle;
	private LocalDate birthDate;
	private LocalDate startDate;
	private LocalDate endDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Calculate the employees current age
	 * @return
	 */
	public int calculateAge() {
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
	}

}

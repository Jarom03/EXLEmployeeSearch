package com.schertz.assessment.models;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Jarom Schertz An entity object representing an employee
 */
@Entity
public class Employee {

//	name, job title, age, start and end date of employment.
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private LocalDate birthDate;
	private LocalDate startDate;
	private LocalDate endDate;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Transient
	private int age;

	public String getFirstName() {
		return firstName;
	}

	public void setName(String firstName) {
		this.firstName = firstName;
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

	public int getAge() {
		return calculateAge();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Calculate the employees current age
	 * @return - years of age as integer
	 */
	public int calculateAge() {
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
	}

}

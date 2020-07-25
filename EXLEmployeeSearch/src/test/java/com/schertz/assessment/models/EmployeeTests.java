package com.schertz.assessment.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jarom Schertz
 * Test the Employee object
 */
@SpringBootTest
public class EmployeeTests {
	
	@Test
	void calculateAge() {
		Employee emp = new Employee();
		emp.setBirthDate(LocalDate.now().minusYears(35));
		assertEquals(emp.calculateAge(), 35);
	}
	
	
}

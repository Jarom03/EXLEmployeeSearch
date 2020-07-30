/**
 * 
 */
package com.schertz.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import com.schertz.assessment.models.Employee;

/**
 * @author Jarom Schertz
 *
 */
@SpringBootTest
public class EmployeeSearchConfigurerTests {

	@Autowired
	private ConversionService conversionService;

	@Test
	public void testConversionService() {
		Employee employee = conversionService.convert(
				"{\"firstName\": \"JRoc\", \"lastName\": \"Wilson\", \"jobTitle\": \"Executive Assistant\",\"birthDate\": \"1954-11-02\",\"startDate\": \"1990-06-11\",\"endDate\": \"1990-03-13\"}",
				Employee.class);
		assertEquals(employee.getFirstName(), "JRoc");
		assertEquals(employee.getLastName(), "Wilson");
		assertEquals(employee.getBirthDate(), LocalDate.of(1954, 11, 02));
	}

}

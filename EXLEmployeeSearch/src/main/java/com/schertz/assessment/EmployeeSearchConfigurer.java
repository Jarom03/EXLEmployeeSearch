package com.schertz.assessment;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.schertz.assessment.models.Employee;

/**
 * @author Jarom Schertz
 * Spring configuration class for the EmployeeSearch application
 */
@Configuration
public class EmployeeSearchConfigurer implements WebMvcConfigurer {

	
	/**
	 * Add custom converter for converting json string to Employee object to app registry
	 * This will allow auto conversion of Employee objects in the RestController
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		
		class JsonToEmployeeConverter implements Converter<String, Employee> {

			@Override
			public Employee convert(String source) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.registerModule(new JavaTimeModule());
				mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				Employee emp = new Employee();
				try {
					emp = mapper.readValue(source, Employee.class);
				} catch (IOException e) {
					System.err.println("Unable to convert " + source + " to Employee object");
					e.printStackTrace();
				}
				return emp;
			}
		}
		
		registry.addConverter(new JsonToEmployeeConverter());
	}
	
	
}

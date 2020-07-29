package com.schertz.assessment;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.schertz.assessment.models.Employee;
import com.schertz.assessment.models.EmployeeRepository;

/**
 * @author Jarom Schertz Spring configuration class for the EmployeeSearch
 *         application
 */
@Configuration
public class EmployeeSearchConfigurer implements WebMvcConfigurer {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Value("classpath:seed-data.json")
	private Resource seedDataResource;

	/**
	 * Add custom converter for converting json string to Employee object to app
	 * registry This will allow auto conversion of Employee objects in the
	 * RestController
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

	/**
	 * redirect all paths that don't exist to the static angular web page
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**/*").addResourceLocations("classpath:/static/").resourceChain(true)
				.addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						Resource requestedResource = location.createRelative(resourcePath);
						return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
								: new ClassPathResource("static/index.html");
					}
				});
	}

	/**
	 * Populate the database with values once the application is started
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void seedDatabase() {
		if (employeeRepo.count() == 0) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			try {
				// read json file
				List<Employee> employees = objectMapper.readValue(seedDataResource.getFile(),
						new TypeReference<List<Employee>>() {
						});

				// save employee to database
				for (Employee emp : employees) {
					employeeRepo.save(emp);
				}
			} catch (IOException e) {
				System.err.println("Error seeding Employee data from json to database");
				e.printStackTrace();
			}

		}
	}

}

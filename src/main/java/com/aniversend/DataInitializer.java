package com.aniversend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aniversend.models.Employee;
import com.aniversend.repositories.EmployeeRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public DataInitializer(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void run(String... args) {
		Date today = new Date();
		List<Employee> employees = new ArrayList<>();

		Employee employee1 = new Employee();
		employee1.setName("Funcionário 1");
		employee1.setBirthday(today);
		employee1.setEmail("teste@lewoaragao.com.br");
		employees.add(employee1);

		Employee employee2 = new Employee();
		employee2.setName("Funcionário 2");
		employee2.setBirthday(today);
		employee2.setEmail("contato@lewoaragao.com.br");
		employees.add(employee2);

		for (Employee employee : employees) {
			if (employee != null && employeeRepository.findByEmail(employee.getEmail()) == null) {
				employeeRepository.save(employee);
			}
		}
	}
}

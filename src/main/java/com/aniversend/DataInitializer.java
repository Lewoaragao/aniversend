package com.aniversend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aniversend.models.People;
import com.aniversend.repositories.PeopleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private final PeopleRepository employeeRepository;

	@Autowired
	public DataInitializer(PeopleRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void run(String... args) {
		Date today = new Date();
		List<People> employees = new ArrayList<>();

		People employee1 = new People();
		employee1.setName("Traduzidor");
		employee1.setBirthday(today);
		employee1.setEmail("traduzidor@gmail.com");
		employees.add(employee1);

//		Employee employee2 = new Employee();
//		employee2.setName("Adota Pet Org");
//		employee2.setBirthday(today);
//		employee2.setEmail("adotapetorg@gmail.com");
//		employees.add(employee2);
//
//		Employee employee3 = new Employee();
//		employee3.setName("Lewoaragao");
//		employee3.setBirthday(today);
//		employee3.setEmail("contato@lewoaragao.com.br");
//		employees.add(employee3);

		for (People employee : employees) {
			if (employee != null && employeeRepository.findByEmail(employee.getEmail()) == null) {
				employeeRepository.save(employee);
			}
		}
	}
}

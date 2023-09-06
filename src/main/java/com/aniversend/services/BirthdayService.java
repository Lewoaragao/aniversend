package com.aniversend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aniversend.models.Employee;
import com.aniversend.repositories.EmployeeRepository;

@Service
public class BirthdayService {
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 10000) // Executa a cada 10 segundos
    public void printConsoleMessage() {
    	
        Date today = new Date();
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> employeesWithBirthday = employeeRepository.findByBirthday(today);
        
        System.out.println("Numero de employees encontrados: " + employees.size());
        System.out.println("Numero de employees aniversariando hoje encontrados: " + employeesWithBirthday.size());

        for (Employee employee : employeesWithBirthday) {
            String subject = "Feliz Aniversário!";
            String message = "<strong>Parabéns, " + employee.getName() + "!</strong> <br /> Desejamos a você um dia maravilhoso!";
            String recipientEmail = employee.getEmail();

            emailService.sendEmail(recipientEmail, subject, message);
            System.out.println("E-mail enviado para: " + employee.getEmail());
        }
    }
}

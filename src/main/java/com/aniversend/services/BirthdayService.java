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

//    @Scheduled(cron = "0 50 6 * * ?") // Executa todos os dias às 06:50 da manhã
    @Scheduled(fixedRate = 30000) // Executa a cada 30 segundos
    public void printConsoleMessageAndSendEmail() {
        Date today = new Date();
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> employeesWithBirthday = employeeRepository.findByBirthday(today);
        
        System.out.println("Numero de employees encontrados: " + employees.size());
        System.out.println("Numero de employees aniversariando hoje encontrados: " + employeesWithBirthday.size());

        for (Employee employee : employeesWithBirthday) {
            String subject = "Feliz Aniversário!";
//            String message = "<strong>Parabéns, " + employee.getName() + "!</strong> <br /> Desejamos a você um dia maravilhoso!";
            String message = "<!DOCTYPE html>\r\n"
            		+ "<html lang=\"en\">\r\n"
            		+ "<head>\r\n"
            		+ "    <meta charset=\"UTF-8\">\r\n"
            		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
            		+ "    <title>Parabéns!</title>\r\n"
            		+ "    <style>\r\n"
            		+ "        /* Estilos para o corpo do e-mail */\r\n"
            		+ "        body {\r\n"
            		+ "            font-family: Arial, sans-serif;\r\n"
            		+ "            background-color: #f4f4f4;\r\n"
            		+ "            padding: 20px;\r\n"
            		+ "            text-align: center;\r\n"
            		+ "        }\r\n"
            		+ "\r\n"
            		+ "        /* Estilos para o contêiner principal */\r\n"
            		+ "        .container {\r\n"
            		+ "            background-color: #ffffff;\r\n"
            		+ "            border-radius: 10px;\r\n"
            		+ "            padding: 20px;\r\n"
            		+ "            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\r\n"
            		+ "        }\r\n"
            		+ "\r\n"
            		+ "        /* Estilos para o título */\r\n"
            		+ "        h1 {\r\n"
            		+ "            color: #333;\r\n"
            		+ "        }\r\n"
            		+ "\r\n"
            		+ "        /* Estilos para a mensagem de parabéns */\r\n"
            		+ "        .message {\r\n"
            		+ "            font-size: 18px;\r\n"
            		+ "            color: #666;\r\n"
            		+ "            margin-top: 20px;\r\n"
            		+ "        }\r\n"
            		+ "\r\n"
            		+ "        /* Estilos para o botão de ação */\r\n"
            		+ "        .action-button {\r\n"
            		+ "            display: inline-block;\r\n"
            		+ "            background-color: #007bff;\r\n"
            		+ "            color: #fff;\r\n"
            		+ "            padding: 10px 20px;\r\n"
            		+ "            border-radius: 5px;\r\n"
            		+ "            text-decoration: none;\r\n"
            		+ "            margin-top: 20px;\r\n"
            		+ "        }\r\n"
            		+ "\r\n"
            		+ "        /* Estilos para o rodapé */\r\n"
            		+ "        .footer {\r\n"
            		+ "            margin-top: 20px;\r\n"
            		+ "            color: #888;\r\n"
            		+ "        }\r\n"
            		+ "    </style>\r\n"
            		+ "</head>\r\n"
            		+ "<body>\r\n"
            		+ "    <div class=\"container\">\r\n"
            		+ "        <h1>Parabéns!</h1>\r\n"
            		+ "        <div class=\"message\">\r\n"
            		+ "            <p>Parabéns pelo seu aniversário! " + employee.getName() + " desejamos a você um dia repleto de alegria e realizações.</p>\r\n"
            		+ "            <p>Que este novo ano de vida seja cheio de sucesso e felicidade.</p>\r\n"
            		+ "        </div>\r\n"
            		+ "        <a class=\"action-button\" href=\"lewoaragao.com.br\">Celebre conosco</a>\r\n"
            		+ "        <div class=\"footer\">\r\n"
            		+ "            <p>Atenciosamente,</p>\r\n"
            		+ "            <p>Sua Equipe</p>\r\n"
            		+ "        </div>\r\n"
            		+ "    </div>\r\n"
            		+ "</body>\r\n"
            		+ "</html>\r\n"
            		+ "";
            String recipientEmail = employee.getEmail();

            emailService.sendEmail(recipientEmail, subject, message);
            System.out.println("E-mail enviado para: " + employee.getEmail());
        }
    }
}

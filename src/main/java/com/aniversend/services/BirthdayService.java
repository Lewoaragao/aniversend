package com.aniversend.services;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aniversend.models.People;
import com.aniversend.repositories.PeopleRepository;

@Service
public class BirthdayService {
	
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private EmailService emailService;

//    @Scheduled(cron = "0 50 6 * * ?") // Executa todos os dias às 06:50 da manhã
    @Scheduled(fixedRate = 30000) // Executa a cada 30 segundos
    public void printConsoleMessageAndSendEmail() {
        Date today = new Date();
        List<People> peoples = peopleRepository.findAll();
        List<People> peoplesWithBirthday = peopleRepository.findByBirthday(today);
        
        Logger.getGlobal().info("Number of peoples found: " + peoples.size());
        Logger.getGlobal().info("Number of peoples celebrating their birthday today: " + peoplesWithBirthday.size());

        for (People people : peoplesWithBirthday) {
            String subject = "Happy birthday " + people.getName() + "!";
            String message = this.getMessage(people);
            String recipientEmail = people.getEmail();

            emailService.sendEmail(recipientEmail, subject, message);
            Logger.getGlobal().info("Email sent to: " + people.getEmail());
        }
    }
    
    public String getMessage(People people) {
    	return "<!DOCTYPE html>\r\n"
        		+ "<html lang=\"en\">\r\n"
        		+ "<head>\r\n"
        		+ "    <meta charset=\"UTF-8\">\r\n"
        		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
        		+ "    <title>Parabéns!</title>\r\n"
        		+ "    <style>\r\n"
        		+ "        /* Email Body Styles */\r\n"
        		+ "        body {\r\n"
        		+ "            font-family: Arial, sans-serif;\r\n"
        		+ "            background-color: #f4f4f4;\r\n"
        		+ "            padding: 20px;\r\n"
        		+ "            text-align: center;\r\n"
        		+ "        }\r\n"
        		+ "\r\n"
        		+ "        /* Styles for the main container */\r\n"
        		+ "        .container {\r\n"
        		+ "            background-color: #ffffff;\r\n"
        		+ "            border-radius: 10px;\r\n"
        		+ "            padding: 20px;\r\n"
        		+ "            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\r\n"
        		+ "        }\r\n"
        		+ "\r\n"
        		+ "        /* Styles for the title */\r\n"
        		+ "        h1 {\r\n"
        		+ "            color: #333;\r\n"
        		+ "        }\r\n"
        		+ "\r\n"
        		+ "        /* Congratulations message styles */\r\n"
        		+ "        .message {\r\n"
        		+ "            font-size: 18px;\r\n"
        		+ "            color: #666;\r\n"
        		+ "            margin-top: 20px;\r\n"
        		+ "        }\r\n"
        		+ "\r\n"
        		+ "        /* Styles for the action button */\r\n"
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
        		+ "        /* Footer styles */\r\n"
        		+ "        .footer {\r\n"
        		+ "            margin-top: 20px;\r\n"
        		+ "            color: #888;\r\n"
        		+ "        }\r\n"
        		+ "    </style>\r\n"
        		+ "</head>\r\n"
        		+ "<body>\r\n"
        		+ "    <div class=\"container\">\r\n"
        		+ "        <h1>Congratulations!</h1>\r\n"
        		+ "        <div class=\"message\">\r\n"
        		+ "            <p>Congratulations on your birthday! " + people.getName() + " we wish you a day full of joy and achievements.</p>\r\n"
        		+ "            <p>May this new year of life be full of success and happiness.</p>\r\n"
        		+ "        </div>\r\n"
        		+ "        <a class=\"action-button\" href=\"github.com/lewoaragao/AniverSend\">Celebrate with us</a>\r\n"
        		+ "        <div class=\"footer\">\r\n"
        		+ "            <p>Yours sincerely,</p>\r\n"
        		+ "            <p>Your team</p>\r\n"
        		+ "        </div>\r\n"
        		+ "    </div>\r\n"
        		+ "</body>\r\n"
        		+ "</html>\r\n"
        		+ "";
    }
}

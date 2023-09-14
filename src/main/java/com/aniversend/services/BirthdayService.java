package com.aniversend.services;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${number.email.template}")
	Integer numberEmailTemplate;

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
			String message = this.getMessage(people, numberEmailTemplate);
			String recipientEmail = people.getEmail();

//			emailService.sendEmail(recipientEmail, subject, message);
//			Logger.getGlobal().info("Email sent to: " + people.getEmail());
		}
	}

	public String getMessage(People people, Integer numberTemplate) {

		String template = "";

		switch (numberTemplate) {
		case 1:
	    	template = "<!DOCTYPE html>\r\n"
	    			+ "<html lang=\"en\">\r\n"
	    			+ "  <head>\r\n"
	    			+ "    <meta charset=\"UTF-8\" />\r\n"
	    			+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
	    			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
	    			+ "    <title>Happy Birthday</title>\r\n"
	    			+ "    <style>\r\n"
	    			+ "      body {\r\n"
	    			+ "        font-family: Arial, sans-serif;\r\n"
	    			+ "        background-color: #f4f4f4;\r\n"
	    			+ "        padding: 20px;\r\n"
	    			+ "        text-align: center;\r\n"
	    			+ "      }\r\n"
	    			+ "\r\n"
	    			+ "      .container {\r\n"
	    			+ "        background-color: #ffffff;\r\n"
	    			+ "        border-radius: 10px;\r\n"
	    			+ "        padding: 20px;\r\n"
	    			+ "        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\r\n"
	    			+ "      }\r\n"
	    			+ "\r\n"
	    			+ "      h1 {\r\n"
	    			+ "        color: #333;\r\n"
	    			+ "      }\r\n"
	    			+ "\r\n"
	    			+ "      .message {\r\n"
	    			+ "        font-size: 18px;\r\n"
	    			+ "        color: #666;\r\n"
	    			+ "        margin-top: 20px;\r\n"
	    			+ "      }\r\n"
	    			+ "\r\n"
	    			+ "      .footer {\r\n"
	    			+ "        margin-top: 20px;\r\n"
	    			+ "        color: #888;\r\n"
	    			+ "      }\r\n"
	    			+ "\r\n"
	    			+ "      .btn {\r\n"
	    			+ "        display: inline-block;\r\n"
	    			+ "        padding: 10px;\r\n"
	    			+ "        background-color: #2c2c2c;\r\n"
	    			+ "        color: #d1d1d1;\r\n"
	    			+ "        text-decoration: none;\r\n"
	    			+ "        border-radius: 5px;\r\n"
	    			+ "        margin-top: 20px;\r\n"
	    			+ "        transition: opacity 0.3s;\r\n"
	    			+ "      }\r\n"
	    			+ "\r\n"
	    			+ "      .btn:hover {\r\n"
	    			+ "        opacity: 0.8;\r\n"
	    			+ "      }\r\n"
	    			+ "    </style>\r\n"
	    			+ "  </head>\r\n"
	    			+ "  <body>\r\n"
	    			+ "    <div class=\"container\">\r\n"
	    			+ "      <h1>🎉</h1>\r\n"
	    			+ "      <h1>Congratulations!</h1>\r\n"
	    			+ "      <div class=\"message\">\r\n"
	    			+ "        <p>\r\n"
	    			+ "          Congratulations on your birthday! " + people.getName() + " we wish\r\n"
	    			+ "          you a day full of joy and achievements.\r\n"
	    			+ "        </p>\r\n"
	    			+ "        <p>May this new year of life be full of success and happiness.</p>\r\n"
	    			+ "      </div>\r\n"
	    			+ "\r\n"
	    			+ "      <div class=\"footer\">\r\n"
	    			+ "        <p>Yours sincerely,</p>\r\n"
	    			+ "        <p>Your team</p>\r\n"
	    			+ "      </div>\r\n"
	    			+ "\r\n"
	    			+ "      <a\r\n"
	    			+ "        class=\"btn\"\r\n"
	    			+ "        target=\"_blank\"\r\n"
	    			+ "        href=\"https://github.com/lewoaragao/AniverSend\"\r\n"
	    			+ "        target=\"_blank\"\r\n"
	    			+ "        >&copy; AniverSend</a\r\n"
	    			+ "      >\r\n"
	    			+ "    </div>\r\n"
	    			+ "  </body>\r\n"
	    			+ "</html>\r\n"
	    			+ "";
	    	break;
	    	
		case 2:
			template = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "  <head>\r\n"
					+ "    <meta charset=\"UTF-8\" />\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
					+ "    <title>Happy Birthday</title>\r\n"
					+ "    <style>\r\n"
					+ "      body {\r\n"
					+ "        background-color: #f4f4f4;\r\n"
					+ "        padding: 20px;\r\n"
					+ "        text-align: center;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .container {\r\n"
					+ "        background-color: #ffffff;\r\n"
					+ "        border-radius: 10px;\r\n"
					+ "        padding: 20px;\r\n"
					+ "        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "      }\r\n"
					+ "      h1 {\r\n"
					+ "        color: #333;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .message {\r\n"
					+ "        font-size: 18px;\r\n"
					+ "        color: #666;\r\n"
					+ "        margin-top: 20px;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .footer {\r\n"
					+ "        margin-top: 20px;\r\n"
					+ "        color: #888;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .btn {\r\n"
					+ "        font-family: Arial, sans-serif;\r\n"
					+ "        display: inline-block;\r\n"
					+ "        padding: 10px;\r\n"
					+ "        background-color: #2e2e2e;\r\n"
					+ "        color: #d1d1d1;\r\n"
					+ "        text-decoration: none;\r\n"
					+ "        border-radius: 5px;\r\n"
					+ "        margin-top: 10px;\r\n"
					+ "      }\r\n"
					+ "    </style>\r\n"
					+ "  </head>\r\n"
					+ "  <body>\r\n"
					+ "    <div class=\"container\">\r\n"
					+ "      <h1>🎂 Happy Birthday " + people.getName() + "! 🎉</h1>\r\n"
					+ "      <div class=\"message\">\r\n"
					+ "        <p>Wishing you a fantastic day filled with joy and happiness.</p>\r\n"
					+ "        <p>May all your dreams and wishes come true.</p>\r\n"
					+ "        <p>Enjoy your special day!</p>\r\n"
					+ "      </div>\r\n"
					+ "\r\n"
					+ "      <div class=\"footer\">\r\n"
					+ "        <p>Yours sincerely,</p>\r\n"
					+ "        <p>Your team</p>\r\n"
					+ "      </div>\r\n"
					+ "\r\n"
					+ "      <a\r\n"
					+ "        class=\"btn\"\r\n"
					+ "        target=\"_blank\"\r\n"
					+ "        href=\"https://github.com/lewoaragao/AniverSend\"\r\n"
					+ "        target=\"_blank\"\r\n"
					+ "        >&copy; AniverSend</a\r\n"
					+ "      >\r\n"
					+ "    </div>\r\n"
					+ "  </body>\r\n"
					+ "</html>\r\n"
					+ "";
			break;
		case 3:
			template = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "  <head>\r\n"
					+ "    <meta charset=\"UTF-8\" />\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
					+ "    <title>Birthday Greeting</title>\r\n"
					+ "    <style>\r\n"
					+ "      body {\r\n"
					+ "        font-family: Arial, sans-serif;\r\n"
					+ "        background-color: #f4f4f4;\r\n"
					+ "        padding: 20px;\r\n"
					+ "        text-align: center;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .container {\r\n"
					+ "        background-color: #ffffff;\r\n"
					+ "        border-radius: 10px;\r\n"
					+ "        padding: 20px;\r\n"
					+ "        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      h1 {\r\n"
					+ "        color: #ff6347;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      p {\r\n"
					+ "        color: #333;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .birthday-image {\r\n"
					+ "        max-width: 100%;\r\n"
					+ "        border-radius: 10px;\r\n"
					+ "        margin: 20px 0;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .btn {\r\n"
					+ "        display: inline-block;\r\n"
					+ "        padding: 10px;\r\n"
					+ "        background-color: #ff6347;\r\n"
					+ "        color: #fff;\r\n"
					+ "        text-decoration: none;\r\n"
					+ "        border-radius: 5px;\r\n"
					+ "        margin-top: 20px;\r\n"
					+ "        transition: opacity 0.3s;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .btn:hover {\r\n"
					+ "        opacity: 0.8;\r\n"
					+ "      }\r\n"
					+ "    </style>\r\n"
					+ "  </head>\r\n"
					+ "  <body>\r\n"
					+ "    <div class=\"container\">\r\n"
					+ "      <h1>🎂 Happy Birthday  " + people.getName() + "! 🎉</h1>\r\n"
					+ "\r\n"
					+ "      <p>Wishing you a day filled with love, laughter, and joy.</p>\r\n"
					+ "      <p>May your year ahead be as bright and colorful as this message!</p>\r\n"
					+ "      <p>Celebrate and enjoy your special day!</p>\r\n"
					+ "\r\n"
					+ "      <a\r\n"
					+ "        class=\"btn\"\r\n"
					+ "        target=\"_blank\"\r\n"
					+ "        href=\"https://github.com/lewoaragao/AniverSend\"\r\n"
					+ "        target=\"_blank\"\r\n"
					+ "        >&copy; AniverSend</a\r\n"
					+ "      >\r\n"
					+ "    </div>\r\n"
					+ "  </body>\r\n"
					+ "</html>\r\n"
					+ "";
			break;
			
		default:
			template = "Happy Birthday " + people.getName() + "!";
			break;
		}
		
		return template;
	}
}

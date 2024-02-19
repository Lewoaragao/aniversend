package com.aniversend.services;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aniversend.models.People;
import com.aniversend.repositories.PeopleRepository;
import com.aniversend.util.MailTemplate;

@Service
public class BirthdayService {

	private final PeopleRepository peopleRepository;

	private final EmailService emailService;

	@Value("${number.email.template}")
	Integer numberEmailTemplate;

	public BirthdayService(PeopleRepository peopleRepository, EmailService emailService) {
		this.peopleRepository = peopleRepository;
		this.emailService = emailService;
	}

	// @Scheduled(cron = "0 50 6 * * ?") // Runs every day at 6:50 am
	@Scheduled(fixedRate = 30000) // Runs every 30 seconds
	public void printConsoleMessageAndSendEmail() {
		Date today = new Date();
		List<People> peoplesWithBirthday = peopleRepository.findByBirthday(today);
		long numberOfPeoples = peoplesWithBirthday.size();

		Logger.getGlobal().info("Number of peoples found: " + numberOfPeoples);
		Logger.getGlobal().info("Number of peoples celebrating their birthday today: " + peoplesWithBirthday.size());

		for (People people : peoplesWithBirthday) {
			String subject = "Happy birthday " + people.getName() + "!";
			String message = MailTemplate.getMessage(people, numberEmailTemplate);
			String recipientEmail = people.getEmail();

			emailService.sendEmail(recipientEmail, subject, message);
			Logger.getGlobal().info("Email sent to: " + people.getEmail());
		}
	}
}

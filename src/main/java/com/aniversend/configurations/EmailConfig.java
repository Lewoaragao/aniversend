package com.aniversend.configurations;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

//    	Gmail
		mailSender.setHost("smtp.gmail.com"); // Host SMTP
		mailSender.setPort(465); // Porta SMTP
		mailSender.setUsername("naoresponsaemailautomatico@gmail.com"); // Seu email
		mailSender.setPassword("yfuavbzmwbjbdugt"); // Sua senha
		mailSender.setDefaultEncoding("UTF-8");

//		Configurações Spring JavaMailSenderImpl Properties
        Properties mailProp = mailSender.getJavaMailProperties();
        mailProp.put("mail.transport.protocol", "smtp");
        mailProp.put("mail.smtp.auth", "true");
        mailProp.put("mail.smtp.ssl.enable", "true");
        
        return mailSender;
    }
}

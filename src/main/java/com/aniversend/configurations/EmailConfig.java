package com.aniversend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
    	
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("app.debugmail.io"); // Host SMTP
        mailSender.setPort(25); // Porta SMTP
        mailSender.setUsername("2c875b97-c0a1-4f13-b767-c6fbbd816f87"); // Seu email
        mailSender.setPassword("08d58bb6-a422-4860-b224-c6d85bfa2a51"); // Sua senha
        mailSender.setDefaultEncoding("UTF-8");

//        Configurações adicionais
//        mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
//        mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
//        mailSender.getJavaMailProperties().setProperty("mail.smtp.ssl.enable", "true"); 

        return mailSender;
        
    }
}

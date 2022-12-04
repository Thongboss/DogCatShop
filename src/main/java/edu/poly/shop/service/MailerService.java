package edu.poly.shop.service;

import javax.mail.MessagingException;

import edu.poly.shop.domain.MailInfo;

public interface MailerService {

	void queue(String to, String subject, String body);

	void queue(MailInfo mail);

	void send(String to, String subject, String body) throws MessagingException;

	void send(MailInfo mail) throws MessagingException;

}

package com.antunmod.pricetag.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.User;
import com.antunmod.pricetag.model.transfer.UserInformation;
import com.antunmod.pricetag.repo.UserRepository;

/*
 *	 This is the Service class which handles user data.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private final String PRICETAG_EMAIL = "pricetag.application@gmail.com";

	public User getUser(String username, String password) {
		User user = userRepository.findByUserNameAndPassword(username, password);
		if (user == null) {
			return new User();
		}
		return user;
	}

	public User saveUser(User user) {

		User savedUser = userRepository.save(user);
		if (user == null) {
			return new User();
		}
		return savedUser;
	}

	public UserInformation getUserInformation(Short id) {
		List<Object[]> objectInformation = userRepository.findUserInformation(id);
		if (objectInformation == null)
			return null;
		UserInformation userInformation = null;
		for (Object[] o : objectInformation) {
			userInformation = new UserInformation((String) o[0], (String) o[1], (Short) o[2], (String) o[3],
					((BigInteger) o[4]).intValue(), ((BigInteger) o[5]).intValue(), (String) o[6], (Date) o[7]);
		}
		return userInformation;
	}

	public void awardPointsToUser(Short userId, Integer points) {
		User user = userRepository.findById(userId);
		Integer newUserPoints = user.getPoints() + points;
		user.setPoints(newUserPoints.shortValue());
		userRepository.save(user);
	}

	public Boolean sendPasswordToEmail(String username, String email) {
		String password = userRepository.findPasswordForUsernameAndEmail(username, email);
		if (password == null)
			return false;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, 
			    new javax.mail.Authenticator(){
			        protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(
			                PRICETAG_EMAIL, "g1pricetag");// Specify the Username and the PassWord
			        }
			});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(PRICETAG_EMAIL));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Zatražena lozinka");
			message.setText("Poštovani,\nU aplikaciju su uneseni vaši podaci korisničko ime i adresa elektroničke pošte te je "
					+ "zatraženo slanje lozinke koja odgovara unesenim podacima.\n"
					+ "Vaši korisnički podaci su sljedeći:\n\n"
					+ "korisničko ime: " + username + "\nlozinka: " + password + "\n\n"
							+ "PriceTag");

			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}

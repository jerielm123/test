package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Email;
import com.example.demo.entity.Task;
import com.example.demo.repository.EmailRepository;
import com.example.demo.repository.TaskGroupRepository;
import com.example.demo.repository.TaskRepository;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

@Service
public class EmailServiceImpl implements EmailService {

	private static final String APPLICATION_NAME = "Email Application";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	private final TaskRepository taskRepository;
	private final TaskGroupRepository taskGroupRepository;
	private final EmailRepository emailRepository;
	private final TokenService tokenService;
	private final JavaMailSender mailSender;

	public EmailServiceImpl(TaskRepository taskRepository, TokenService tokenService,
			TaskGroupRepository taskGroupRepository, EmailRepository emailRepository,
			JavaMailSender mailSender) {
		this.taskRepository = taskRepository;
		this.taskGroupRepository = taskGroupRepository;
		this.emailRepository = emailRepository;
		this.tokenService = tokenService;
		this.mailSender = mailSender;
	}

	@Override
	public void sendEosReport(String accessToken, Long userId) throws IOException, GeneralSecurityException {
		accessToken = tokenService.getAccessTokenFromIdToken(accessToken);
		GoogleCredentials credentials = GoogleCredentials.create(new AccessToken(accessToken, null));
		Gmail service;
		String body = getTasks(userId);

		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String dateString = currentDate.format(formatter);
		String subject = "EOS Report - " + dateString;

		List<Email> recipientList = emailRepository.findByUserSettings(userId);

		try {
			service = createGmailService(credentials);
			MimeMessage email = createEmail(recipientList, subject, body);
			sendMessage(service, email);
		} catch (GeneralSecurityException | IOException | MessagingException e) {
			e.printStackTrace();
		}

		updateTasksStatus(userId);
	}

	@Override
	public void sendEosReport(Long userId) {
		List<Email> recipientList = emailRepository.findByUserSettings(userId);
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String dateString = currentDate.format(formatter);
		String subject = "EOS Report - " + dateString;
		for(Email recipient : recipientList) {
			sendSimpleMessage(recipient.getEmailAddress(), subject, getTasks(userId));
		}
		updateTasksStatus(userId);

	}

	private static Gmail createGmailService(GoogleCredentials credentials)
			throws GeneralSecurityException, IOException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

		return new Gmail.Builder(httpTransport, JSON_FACTORY, requestInitializer).setApplicationName(APPLICATION_NAME)
				.build();
	}

	private static MimeMessage createEmail(List<Email> recipientList, String subject, String bodyText)
			throws MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress("me"));
		for (Email recipient : recipientList) {
			if (recipient.getEmailType() == "To") {
				email.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress(recipient.getEmailAddress()));
			} else if (recipient.getEmailType() == "CC") {
				email.addRecipient(javax.mail.Message.RecipientType.CC,
						new InternetAddress(recipient.getEmailAddress()));
			}

		}

		email.setSubject(subject);
		email.setText(bodyText);
		return email;
	}

	private static Message sendMessage(Gmail service, MimeMessage emailContent) throws MessagingException, IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		emailContent.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.getUrlEncoder().encodeToString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);

		return service.users().messages().send("me", message).execute();
	}

	private String getTasks(Long userId) {
		List<Task> doneTaskList = taskRepository.getCurrentTasks(userId);
		String message = "What I did today:\n";
		for (Task task : doneTaskList) {
			if(task.getIsDone()) {
			   message = message + "\t-" + task.getDescription() + "\n";
			}
		}

		return message;
	}

	private void updateTasksStatus(Long userId) {
		List<Task> doneTaskList = taskRepository.getDoneTasks(userId);
		LocalDate date = LocalDate.now();

		for (Task task : doneTaskList) {
			taskGroupRepository.updateTaskGroupById(task.getTaskGroup().getId(), true, date, userId);
			taskRepository.updateTaskById(task.getId(), true, date, userId);
		}
	}
	
	public void sendSimpleMessage(
    	      String to, String subject, String text) {
    	        SimpleMailMessage message = new SimpleMailMessage(); 
    	        message.setFrom("noreply@baeldung.com");
    	        message.setTo(to); 
    	        message.setSubject(subject); 
    	        message.setText(text);
    	        mailSender.send(message);
    	    }
}
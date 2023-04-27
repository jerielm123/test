package com.example.demo.service;

import com.example.demo.entity.Email;
import com.example.demo.entity.Task;
import com.example.demo.entity.UserSettings;
import com.example.demo.repository.TaskGroupRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserSettingsRepository;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Properties;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService 
{
	private static final String APPLICATION_NAME = "Email Application";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    
    private final TaskRepository taskRepository;
    private final UserSettingsRepository userSettingsRepository;
    private final TaskGroupRepository taskGroupRepository;
    
    public EmailServiceImpl(TaskRepository taskRepository, UserSettingsRepository userSettingsRepository,
    		TaskGroupRepository taskGroupRepository)
    {
    	this.taskRepository = taskRepository;
    	this.userSettingsRepository = userSettingsRepository;
    	this.taskGroupRepository = taskGroupRepository;
    }
 
    @Override
    public void sendEosReport(String accessToken, Long userId) 
    {
    	GoogleCredentials credentials = GoogleCredentials.create(new AccessToken(accessToken, null));
        Gmail service;
        String body = getTasks(userId);
        
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = currentDate.format(formatter);
        String subject = "EOS Report - " + dateString;
        
        List<Email> recipientList = getUserEmailSettings(userId);
        
        try 
        {
            service = createGmailService(credentials);
            MimeMessage email = createEmail(recipientList, subject, body);
            sendMessage(service, email);
        } catch (GeneralSecurityException | IOException | MessagingException e) 
        {
            e.printStackTrace();
        }
        
        updateTasksStatus(userId);
    }

    private static Gmail createGmailService(GoogleCredentials credentials) throws GeneralSecurityException, IOException 
    {
    	HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        return new Gmail.Builder(httpTransport, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static MimeMessage createEmail(List<Email> recipientList, String subject, String bodyText) throws MessagingException 
    {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress("me"));
        for(Email recipient: recipientList)
        {
        	if (recipient.getEmailType() == "To")
        	{
        		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient.getEmailAddress()));
        	}
        	else if (recipient.getEmailType() == "CC") 
			{
        		email.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(recipient.getEmailAddress()));
        	}
        	
        }
        
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    private static Message sendMessage(Gmail service, MimeMessage emailContent) throws MessagingException, IOException 
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.getUrlEncoder().encodeToString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        return service.users().messages().send("me", message).execute();
    }
    
    private String getTasks(Long userId)
    {
    	List<Task> doneTaskList = taskRepository.getDoneTasks(userId);
    	String message = "What I did today:\n";
    	for(Task task : doneTaskList)
    	{
    		message = message + "\t-" + task.getDescription() + "\n";
    	}
    	
    	return message;
    }
    
    private List<Email> getUserEmailSettings(Long userId)
    {
    	UserSettings userSettings = userSettingsRepository.getUserSettings(userId);
    	List<Email> emailList = userSettings.getEmail();
    	return emailList;
    }
    
    private void updateTasksStatus(Long userId)
    {
    	List<Task> doneTaskList = taskRepository.getDoneTasks(userId);
    	LocalDate date = LocalDate.now();
    	
    	for(Task task : doneTaskList)
    	{
    		taskGroupRepository.updateTaskGroupById(task.getTaskGroup().getId(), true, date, userId);
    		taskRepository.updateTaskById(task.getId(), true, date, userId);
    	}
    }
}
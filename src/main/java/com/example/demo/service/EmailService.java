package com.example.demo.service;

public interface EmailService {
	void sendEosReport(String accessToken, String to, String subject, String text);
}

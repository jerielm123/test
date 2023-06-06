package com.example.demo.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface EmailService 
{
	void sendEosReport(String accessToken, Long userId) throws IOException,GeneralSecurityException;
	
	void sendEosReport(Long userId);
}
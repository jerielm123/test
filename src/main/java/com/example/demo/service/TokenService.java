package com.example.demo.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface TokenService {
	
	public String getAccessTokenFromIdToken(String idToken) throws IOException, GeneralSecurityException;

}

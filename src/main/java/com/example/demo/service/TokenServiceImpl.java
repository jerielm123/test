package com.example.demo.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier.Builder;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String CLIENT_ID = "271916097720-5palao1ajigcoeignejl70ot8noj07ec.apps.googleusercontent.com";

    public String getAccessTokenFromIdToken(String idToken) throws IOException, GeneralSecurityException {
        JsonFactory jsonFactory = new JacksonFactory();
        NetHttpTransport httpTransport = new NetHttpTransport();
        Builder verifierBuilder = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID));

        GoogleIdTokenVerifier verifier = verifierBuilder.build();
        GoogleIdToken token = verifier.verify(idToken);

        if (token != null) {
            return token.getPayload().get("access_token").toString();
        }

        return null;
    }
}

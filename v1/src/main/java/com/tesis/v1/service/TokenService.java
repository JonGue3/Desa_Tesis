package com.tesis.v1.service;

import com.tesis.v1.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
}

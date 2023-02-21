package com.t3h.ecommerce.service;

import com.t3h.ecommerce.dto.request.SignInForm;
import com.t3h.ecommerce.dto.request.SignUpForm;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> signUp(SignUpForm signUpForm);
    ResponseEntity<?> signIn(SignInForm signInForm);
}

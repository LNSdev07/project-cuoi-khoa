package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.request.SignInForm;
import com.t3h.ecommerce.dto.request.SignUpForm;
import com.t3h.ecommerce.dto.response.JwtResponse;
import com.t3h.ecommerce.dto.response.ResponseMessage;
import com.t3h.ecommerce.entities.core.Role;
import com.t3h.ecommerce.entities.core.RoleName;
import com.t3h.ecommerce.entities.core.User;
import com.t3h.ecommerce.security.jwt.JwtProvider;
import com.t3h.ecommerce.security.userprincal.UserPrinciple;
import com.t3h.ecommerce.service.AuthService;
import com.t3h.ecommerce.service.Impl.RoleServiceImpl;
import com.t3h.ecommerce.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

   @Autowired
   private final AuthService service;

   @PostMapping("/signup")
   public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
      return service.signUp(signUpForm);
   }

   @PostMapping("/signin")
   public ResponseEntity<?> signIn(@Valid @RequestBody SignInForm signInForm){
    return service.signIn(signInForm);
   }
}

package com.t3h.ecommerce.controller;

import com.t3h.ecommerce.dto.request.SignUpForm;
import com.t3h.ecommerce.dto.response.JwtResponse;
import com.t3h.ecommerce.dto.response.ProfileResponse;
import com.t3h.ecommerce.dto.response.ResponseMessage;
import com.t3h.ecommerce.entities.core.User;
import com.t3h.ecommerce.security.jwt.JwtProvider;
import com.t3h.ecommerce.security.jwt.JwtTokenFilter;
import com.t3h.ecommerce.security.userprincal.UserPrinciple;
import com.t3h.ecommerce.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/profile")
public class ProfileDetailController {

}

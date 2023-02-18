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

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/user")
    public ResponseEntity<?> getInfoUser(HttpServletRequest httpServletRequest){
        String jwt = jwtTokenFilter.getJwt(httpServletRequest);
        String username = jwtProvider.getUserNameFromToken(jwt);
        User user;
        try{
            user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            ProfileResponse profileResponse = new ProfileResponse(
                    user.getUsername(), user.getFullName(), user.getEmail(),
                    user.getPhoneNumber(), user.getAddress(), user.getGender()
            );
            return new ResponseEntity<>(profileResponse, HttpStatus.OK);

        }catch (UsernameNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("")
    public ResponseEntity<?> changeInfoUser(HttpServletRequest httpServletRequest,
                                            @Valid @RequestBody SignUpForm signUpForm){
        String jwt = jwtTokenFilter.getJwt(httpServletRequest);
        String username = jwtProvider.getUserNameFromToken(jwt);
        User user;
        try{
            user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User bot found"));

            if(userService.existsByUsername(signUpForm.getUserName())){
                return new ResponseEntity<>(new ResponseMessage("nouser"), HttpStatus.OK);
            }
            else if(userService.existsByEmail(signUpForm.getEmail())){
                return new ResponseEntity<>(new ResponseMessage("noemail"), HttpStatus.OK);
            }
            else{
                user.setFullName(signUpForm.getFullName());
                user.setUsername(signUpForm.getUserName());
                user.setAvatar(signUpForm.getAvatar());
                user.setEmail(signUpForm.getEmail());
                user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));

                userService.save(user);

                // tao lai token
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                               signUpForm.getUserName(), signUpForm.getPassword()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtProvider.createToken(authentication);

                UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
                return new ResponseEntity<>(new JwtResponse(token,
                        userPrinciple.getFullName(),
                        userPrinciple.getAuthorities(),
                        userPrinciple.getAvatar()), HttpStatus.OK);

            }

        }catch (UsernameNotFoundException ex){
            return new ResponseEntity<>(new ResponseMessage("fail"), HttpStatus.NOT_FOUND);
        }
    }
}

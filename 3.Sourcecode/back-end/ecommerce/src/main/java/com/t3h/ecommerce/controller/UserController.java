package com.t3h.ecommerce.controller;

import com.t3h.ecommerce.dto.response.ResponseMessage;
import com.t3h.ecommerce.entities.core.User;
import com.t3h.ecommerce.pojo.dto.user.UserDTO;
import com.t3h.ecommerce.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
@CrossOrigin("http://localhost:4200")
public class UserController {

     @Autowired
     private IUserService userService;

     @Autowired
     private ModelMapper mapper;

     @GetMapping("admin/users")
     public ResponseEntity<?> getListUsers(){
         return  userService.getListUserDTO();
     }

     @GetMapping("find/user")
     public ResponseEntity<?> findUser(@RequestParam("textSearch") String textSearch){
         return userService.findUser(textSearch);
     }


     @GetMapping("find/users")
     public ResponseEntity<?> findUserByUserName(@RequestParam("username") String username){
         try{
             User user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
             UserDTO userDTO = mapper.map(user, UserDTO.class);

             return new ResponseEntity<>(userDTO, HttpStatus.OK);

         } catch (UsernameNotFoundException ex){
             return new ResponseEntity<>(new ResponseMessage("Username not found"), HttpStatus.NOT_FOUND);
         }

     }

     @PutMapping("status/user")
     public ResponseEntity<?> changeStatus(@RequestBody @Valid UserDTO userDTO){
         try{
             User user = userService.findByUsername(userDTO.getUsername()).orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
             user.setStatus(userDTO.getStatus());
             userService.save(user);

             return  userService.getListUserDTO();
         }catch (Exception ex){

             return new ResponseEntity<>(new ResponseMessage("Username not found"), HttpStatus.NOT_FOUND);
         }

     }

}

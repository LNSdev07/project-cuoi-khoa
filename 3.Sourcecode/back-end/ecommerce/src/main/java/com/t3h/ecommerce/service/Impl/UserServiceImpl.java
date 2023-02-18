package com.t3h.ecommerce.service.Impl;

import com.t3h.ecommerce.dto.response.ResponseMessage;
import com.t3h.ecommerce.entities.core.User;
import com.t3h.ecommerce.pojo.dto.user.UserDTO;
import com.t3h.ecommerce.pojo.dto.user.UserResponse;
import com.t3h.ecommerce.repositories.IUserRepository;
import com.t3h.ecommerce.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    private final IUserRepository repository;

    @Override
    public Optional<User> findByUsername(String name) {
        return repository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public ResponseEntity<?> getListUserDTO() {
        List<User> userList = repository.getUsers();
        if(!userList.isEmpty() || userList !=  null){
            List<UserDTO>userDTOList =  userList.stream().map(UserDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(new UserResponse(userDTOList, "success", HttpStatus.OK.value()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ResponseMessage("not success"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> findUser(String textSearch) {
        List<User> userList = repository.findUser(textSearch);
        if(!userList.isEmpty() && userList !=  null){
            List<UserDTO>userDTOList =  userList.stream().map(UserDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(new UserResponse(userDTOList, "success", HttpStatus.OK.value()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ResponseMessage("not success"), HttpStatus.OK);
        }
    }
}

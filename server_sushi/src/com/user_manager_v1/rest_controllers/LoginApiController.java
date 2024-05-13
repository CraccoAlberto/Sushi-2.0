package com.user_manager_v1.rest_controllers;

import com.user_manager_v1.models.Login;
import com.user_manager_v1.models.User;
import com.user_manager_v1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LoginApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity authenticateUser(@RequestBody Login login){

        List<String> userEmail = userService.checkUserEmail(login.getEmail());

        if(userEmail.isEmpty() || userEmail == null){
            return new ResponseEntity("L'email inserita non esiste", HttpStatus.NOT_FOUND);
        }

        String hashed_password = userService.checkUserPasswordByEmail(login.getEmail());

        if(!BCrypt.checkpw(login.getPassword(), hashed_password)){
            return new ResponseEntity("Email o password non corretti", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserDetailsByEmail(login.getEmail());

        return new ResponseEntity(user, HttpStatus.OK);
    }
}

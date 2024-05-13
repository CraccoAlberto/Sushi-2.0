package com.user_manager_v1.services;

import com.user_manager_v1.models.User;
import com.user_manager_v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String nome, String cognome, String email, String password){
        return userRepository.registerNewUser(nome, cognome, email, password);
    }

    public List<String> checkUserEmail(String email){
        return userRepository.checkUserEmail(email);
    }

    public String checkUserPasswordByEmail(String email){
        return userRepository.checkUserPasswordByEmail(email);
    }

    public User getUserDetailsByEmail(String email){
        return userRepository.GetUserDetailsByEmail(email);
    }
}

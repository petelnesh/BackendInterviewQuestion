/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.safaricom.hackathon.BackendInterviewQuestion.services;

import ke.safaricom.hackathon.BackendInterviewQuestion.entities.User;
import ke.safaricom.hackathon.BackendInterviewQuestion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author techsavanna
 */
@Service
public class UserService {
     @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }
}

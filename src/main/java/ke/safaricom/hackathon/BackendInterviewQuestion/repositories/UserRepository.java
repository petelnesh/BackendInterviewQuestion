/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.safaricom.hackathon.BackendInterviewQuestion.repositories;

import java.util.Optional;
import ke.safaricom.hackathon.BackendInterviewQuestion.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author techsavanna
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    
}

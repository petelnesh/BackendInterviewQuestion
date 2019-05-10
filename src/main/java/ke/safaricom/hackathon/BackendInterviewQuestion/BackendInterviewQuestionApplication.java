package ke.safaricom.hackathon.BackendInterviewQuestion;


import java.util.Arrays;
import ke.safaricom.hackathon.BackendInterviewQuestion.entities.Role;
import ke.safaricom.hackathon.BackendInterviewQuestion.entities.User;
import ke.safaricom.hackathon.BackendInterviewQuestion.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendInterviewQuestionApplication {
@Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new User(
                    "admin", //username
                    "peterson", //password
Arrays.asList(new Role("USER"), new Role("ACTUATOR")),//roles 
                    true//Active
            ));
        };
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    } 
	public static void main(String[] args) {
		SpringApplication.run(BackendInterviewQuestionApplication.class, args);
	}

}

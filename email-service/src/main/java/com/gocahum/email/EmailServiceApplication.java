package com.gocahum.email;

import com.gocahum.email.model.User;
import com.gocahum.email.repostory.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.hc.client5.http.utils.ByteArrayBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Log4j2
public class EmailServiceApplication implements CommandLineRunner {
    @Autowired
    private Environment env;
    @Autowired
    UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("email usere: ");
        log.info(env.getProperty("email.props.gmail.user"));
        log.info(env.getProperty("email.props.gmail.password"));
        userRepository.save(new User("admin", new BCryptPasswordEncoder().encode("123456"), "ROLE_ADMIN"));
    }
}

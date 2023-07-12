package edu.uady.authservice.repository;

import edu.uady.authservice.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    Optional<UserAuth> findByUserName (String userName);
}

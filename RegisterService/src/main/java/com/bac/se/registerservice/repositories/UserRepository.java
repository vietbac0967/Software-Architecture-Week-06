package com.bac.se.registerservice.repositories;

import com.bac.se.registerservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserByUsernameAndPassword(String username,String password);
}

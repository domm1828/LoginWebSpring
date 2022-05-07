package net.dmoreno.webLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.dmoreno.webLogin.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
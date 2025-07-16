package com.SaloonProj.saloonapointment.UserRepository;

import com.SaloonProj.saloonapointment.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}

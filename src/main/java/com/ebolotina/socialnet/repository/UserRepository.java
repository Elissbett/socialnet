package com.ebolotina.socialnet.repository;

import com.ebolotina.socialnet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends JpaRepository<User, Long>  {

    //TODO: rethink sql
    @Query("select u from User u where u.login = :login and u.password = :password")
    User getUserByCredentials(@Param("login") String login,
                              @Param("password") String password);
}

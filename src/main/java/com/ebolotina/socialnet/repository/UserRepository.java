package com.ebolotina.socialnet.repository;
import com.ebolotina.socialnet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long>  {
}

package com.lcwd.user.service.userRepository;

import com.lcwd.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}

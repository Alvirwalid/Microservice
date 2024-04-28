package com.lcwd.user.service.controller;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.userService.UserService;
import com.lcwd.user.service.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private  UserService service;

    @PostMapping
    public ResponseEntity<BaseResponse>createUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse>getSingleUser(@PathVariable String userId)  {
        System.out.println("Uuuuuuuuuuuuu");
        return service.getUser(userId);
    }

    @GetMapping
    public ResponseEntity<BaseResponse>getAllUser() {
        return service.getAllUser();
    }


}

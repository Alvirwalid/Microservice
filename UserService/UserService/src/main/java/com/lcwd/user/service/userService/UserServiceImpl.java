package com.lcwd.user.service.userService;

import com.lcwd.user.service.constant.BaseConstant;
import com.lcwd.user.service.entity.Rating.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.userRepository.UserRepository;
import com.lcwd.user.service.utils.BaseResponse;
import com.lcwd.user.service.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements  UserService{

    private UserRepository repository;
    private UserUtils utils;
    private RestTemplate restTemplate;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);



    @Override
    public ResponseEntity<BaseResponse> saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        try {

            if(!user.getName().isEmpty() && !user.getEmail().isEmpty()){
                return new ResponseEntity<>(utils.generateSuccessResponse(repository.save(user),
                        SAVE_MESSAGE,SAVE_MESSAGE_BN),HttpStatus.OK);
            }else {
                return new ResponseEntity<>(utils.generateSuccessResponse(null,INVALID_DATA,INVALID_DATA_BN),HttpStatus.BAD_REQUEST);
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getAllUser() {
        try {
            return new ResponseEntity<>(utils.generateSuccessResponse(repository.findAll(),"",""), HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @Override
    public ResponseEntity<BaseResponse> getUser(String userId) {
       try {
           Optional<User> user = Optional.ofNullable(repository.findById(userId).get());
           if(user.isPresent()){

               String forObject = restTemplate.getForObject("http://localhost:8080/ratings/user/a2bf0d25-391a-4632-aa37-96927642becf", String.class);

               System.out.println(forObject);
//               ArrayList forObject= restTemplate.getForObject("http://localhost:8080/ratings/user/a2bf0d25-391a-4632-aa37-96927642becf",ArrayList.class );
//               logger.info("info",forObject);
               return new ResponseEntity<>(utils.generateSuccessResponse(user,"",""),HttpStatus.OK);
           }else {

               System.out.println("Not Present");
               return new ResponseEntity<>(utils.generateSuccessResponse(null,"ID does not exist","আইডি খুজে পাওয়া যাচ্ছে না"),HttpStatus.BAD_REQUEST);
           }

       }catch (Exception e){
           return  new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);

       }


    }

    @Override
    public ResponseEntity<BaseResponse> updateUser(User userId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> deleteUser(String userId) {
      return null;
    }
}

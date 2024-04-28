package com.lcwd.user.service.userService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.lcwd.user.service.entity.Rating.Hotel;
import com.lcwd.user.service.entity.Rating.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.userRepository.UserRepository;
import com.lcwd.user.service.utils.BaseResponse;
import com.lcwd.user.service.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

import static reactor.core.publisher.Operators.as;

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

               ObjectMapper mapper = new ObjectMapper();
               JsonNode baseResponse = restTemplate.getForObject("http://RATING-SERIVE/ratings/user/"+user.get().getUserId(), JsonNode.class);
               BaseResponse ratingResponse=mapper.convertValue(baseResponse, new TypeReference<BaseResponse>() { });

               List<Rating>ratings= (List<Rating>) ratingResponse.getData();

               ObjectMapper ratingMapper = new ObjectMapper();
               List<Rating> pojos = ratingMapper.convertValue(ratingResponse.getData(), new TypeReference<List<Rating>>() { });

               List<Boolean> booleans  =    pojos.stream().map(element->{
                   BaseResponse hotelsByIdRes = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+element.getHotelId(), BaseResponse.class);
                   ObjectMapper hotelMapper = new ObjectMapper();
                   Hotel hotel =hotelMapper.convertValue(hotelsByIdRes.getData(), new TypeReference<Hotel>() {});
                   element.setHotel(hotel);
                   return element.getHotelId().equals("");
               }).collect(Collectors.toList());


               System.out.println(pojos);
              user.get().setRatings(pojos);
               return new ResponseEntity<>(utils.generateSuccessResponse(user,"",""),HttpStatus.OK);
           }else {

               System.out.println("Not Present");
               return new ResponseEntity<>(utils.generateSuccessResponse(null,"ID does not exist","আইডি খুজে পাওয়া যাচ্ছে না"),HttpStatus.BAD_REQUEST);
           }

       }catch (Exception e){
           e.printStackTrace();
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

package com.lcwd.rating.hotel.Rating.service;

import com.lcwd.rating.hotel.Rating.enity.Rating;
import com.lcwd.rating.hotel.Rating.repo.RatingRepository;
import com.lcwd.rating.hotel.Rating.utils.BaseResponse;
import com.lcwd.rating.hotel.Rating.utils.RatingUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RatServiceImpl implements  RatService{

    @Autowired
    private RatingRepository repository;
    @Autowired
    private RatingUtils utils;



    @Override
    public ResponseEntity<BaseResponse> saveUser(Rating rating) {
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        try {

            if(!rating.getUserId().isEmpty() && !rating.getHotelId().isEmpty()){
                return new ResponseEntity<>(utils.generateSuccessResponse(repository.save(rating),
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
    public ResponseEntity<BaseResponse> getAllRating() {
        try {
            return new ResponseEntity<>(utils.generateSuccessResponse(repository.findAll(),"",""), HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @Override

    public ResponseEntity<BaseResponse> getRatingByUser(String userId) {
       try {
           Optional<List<Rating>> user = Optional.ofNullable(repository.findByUserId(userId));
           if(user.isPresent()){
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
    public ResponseEntity<BaseResponse> getRatingByHotel(String hotelId) {
        try {
            Optional<List<Rating>> ratings = Optional.ofNullable(repository.findByHotelId(hotelId));
            if(ratings.isPresent()){
                return new ResponseEntity<>(utils.generateSuccessResponse(ratings,"",""),HttpStatus.OK);
            }else {

                System.out.println("Not Present");
                return new ResponseEntity<>(utils.generateSuccessResponse(null,"ID does not exist","আইডি খুজে পাওয়া যাচ্ছে না"),HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            return  new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public ResponseEntity<BaseResponse> updateRating(Rating userId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> deleterating(String userId) {
      return null;
    }
}

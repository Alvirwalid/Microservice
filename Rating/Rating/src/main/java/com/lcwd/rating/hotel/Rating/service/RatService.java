package com.lcwd.rating.hotel.Rating.service;
import com.lcwd.rating.hotel.Rating.constant.BaseConstant;
import com.lcwd.rating.hotel.Rating.enity.Rating;
import com.lcwd.rating.hotel.Rating.utils.BaseResponse;
import org.springframework.http.ResponseEntity;


public interface RatService extends BaseConstant {

    ResponseEntity<BaseResponse>  saveUser(Rating user);
    ResponseEntity<BaseResponse> getAllRating();
    ResponseEntity<BaseResponse> getRatingByUser(String userId);
    ResponseEntity<BaseResponse> getRatingByHotel(String userId);

    ResponseEntity<BaseResponse> updateRating(Rating userId);
    ResponseEntity<BaseResponse> deleterating(String userId);


}

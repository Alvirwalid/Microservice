package com.lcwd.hotel.HotelService.hotelService;

import com.lcwd.hotel.HotelService.constant.BaseConstant;
import com.lcwd.hotel.HotelService.entity.Hotel;
import com.lcwd.hotel.HotelService.utils.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface HotelService extends BaseConstant {


    ResponseEntity<BaseResponse> saveHotel(Hotel hotel);
    ResponseEntity<BaseResponse> getAllHotel();
    ResponseEntity<BaseResponse> getHotel(String id);

    ResponseEntity<BaseResponse>  updateUser(Hotel hotel);
    ResponseEntity<BaseResponse>   deleteUser(String userId);

}

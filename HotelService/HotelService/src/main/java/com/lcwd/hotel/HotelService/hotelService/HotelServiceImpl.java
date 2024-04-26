package com.lcwd.hotel.HotelService.hotelService;


import com.lcwd.hotel.HotelService.entity.Hotel;
import com.lcwd.hotel.HotelService.reposotory.HotelRepository;
import com.lcwd.hotel.HotelService.utils.BaseResponse;
import com.lcwd.hotel.HotelService.utils.HotelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository repository;
    @Autowired
    private HotelUtils utils;
    @Override
    public ResponseEntity<BaseResponse> saveHotel(Hotel hotel) {

//
        String randomUserId = UUID.randomUUID().toString();
        hotel.setId(randomUserId);
        try {

            if(!hotel.getName().isEmpty() && !hotel.getLocation().isEmpty()){
                return new ResponseEntity<>(utils.generateSuccessResponse(repository.save(hotel),
                        SAVE_MESSAGE,SAVE_MESSAGE_BN), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(utils.generateSuccessResponse(null,INVALID_DATA,INVALID_DATA_BN),HttpStatus.BAD_REQUEST);
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<BaseResponse> getAllHotel() {
        try {
            return new ResponseEntity<>(utils.generateSuccessResponse(repository.findAll(),"",""), HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public ResponseEntity<BaseResponse> getHotel(String id) {
        try {
            Optional<Hotel> hotel = Optional.ofNullable(repository.findById(id).get());
            if(hotel.isPresent()){
                return new ResponseEntity<>(utils.generateSuccessResponse(hotel,"",""),HttpStatus.OK);
            }else {

                System.out.println("Not Present");
                return new ResponseEntity<>(utils.generateSuccessResponse(null,"ID does not exist","আইডি খুজে পাওয়া যাচ্ছে না"),HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            return  new ResponseEntity<>(utils.generateErrorResponse(e),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public ResponseEntity<BaseResponse> updateUser(Hotel hotel) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> deleteUser(String userId) {
        return null;
    }
}

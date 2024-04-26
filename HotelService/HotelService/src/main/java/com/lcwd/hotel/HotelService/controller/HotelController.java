package com.lcwd.hotel.HotelService.controller;


import com.lcwd.hotel.HotelService.entity.Hotel;
import com.lcwd.hotel.HotelService.hotelService.HotelService;
import com.lcwd.hotel.HotelService.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService service;

    @PostMapping
    public ResponseEntity<BaseResponse> createHotel(@RequestBody Hotel hotel){
        return service.saveHotel(hotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse>getSingleHotel(@PathVariable String id)  {
        return service.getHotel(id);
    }

    @GetMapping
    public ResponseEntity<BaseResponse>getAllHotel() {
        return service.getAllHotel();
    }

}

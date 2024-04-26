package com.lcwd.rating.hotel.Rating.controller;
import com.lcwd.rating.hotel.Rating.enity.Rating;
import com.lcwd.rating.hotel.Rating.service.RatService;

import com.lcwd.rating.hotel.Rating.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {


    @Autowired
    private RatService service;

    @PostMapping
    public ResponseEntity<BaseResponse> createRating(@RequestBody Rating user){
        return service.saveUser(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<BaseResponse> getRatingByUser(@PathVariable String userId)  {
        return service.getRatingByUser(userId);
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<BaseResponse> getRatingByHotel(@PathVariable String hotelId)  {
        return service.getRatingByHotel(hotelId);
    }

    @GetMapping
    public ResponseEntity<BaseResponse>getAllRatings() {
        return service.getAllRating();
    }


}

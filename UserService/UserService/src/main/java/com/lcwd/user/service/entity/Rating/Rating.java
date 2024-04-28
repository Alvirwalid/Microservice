package com.lcwd.user.service.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private  String ratingId;
    private  String userId;
    private  String hotelId;
    private  int rating;
    private  String remark;
    private  String feadback;
    private Hotel hotel;
}

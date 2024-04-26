package com.lcwd.rating.hotel.Rating.enity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @Column(name = "ID")
    private  String ratingId;

    @Column(name = "RATING")
    private  int rating;

    @Column(name = "USER_ID")
    private  String userId;

    @Column(name = "HOTEL_ID")
    private  String hotelId;

    @Column(name = "FEADBACK")
    private  String feadback;


}

package com.lcwd.hotel.HotelService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotels")
public class Hotel {

    @Id
    @Column(name = "ID")
    private  String id;
    @Column(name = "NAME")
    private  String name;
    @Column(name = "LOCATION")
    private  String location;
    @Column(name = "ABOUT")
    private  String about;



}

package com.lcwd.user.service.entity;

import com.lcwd.user.service.entity.Rating.Rating;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "micro_user")
public class User {
    @Id
    @Column(name = "ID")
    private  String userId;
    @Column(name = "NAME")
    private  String name;
    @Column(name = "EMAIL")
    private  String email;
    @Column(name = "ABOUT")
    private  String about;
    @Transient
    private List<Rating>ratings=new ArrayList<>();

}

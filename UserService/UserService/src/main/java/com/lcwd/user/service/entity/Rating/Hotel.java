package com.lcwd.user.service.entity.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private  String id;
    private  String name;
    private  String location;
    private  String about;

}

package com.codeacademy.diningreview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;


    private String restaurantName;
    private Integer zipcode;
    private String cuisine;
    private Double overallRating;
    private Double peanut;
    private Double egg;
    private Double diary;

    public Restaurant(Long id) {
    }
}

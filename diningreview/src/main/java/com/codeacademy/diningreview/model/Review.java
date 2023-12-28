package com.codeacademy.diningreview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Restaurant restaurant; //= new Restaurant(getId());  //see how to reference this by id
    private int peanutScore;
    private int eggScore;
    private int diaryScore;
    private String comment;
    @Enumerated
    private ReviewStatus reviewStatus;

}

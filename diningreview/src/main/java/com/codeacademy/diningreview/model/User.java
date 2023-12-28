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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String displayName; //how can i make this just unique to the user
    private String city;
    private String state;
    private int zipCode;
    private Boolean peanutAllergy;
    private Boolean eggAllergy;
    private Boolean diaryAllergy;

}

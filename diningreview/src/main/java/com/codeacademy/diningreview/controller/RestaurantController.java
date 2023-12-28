package com.codeacademy.diningreview.controller;

import com.codeacademy.diningreview.model.Restaurant;
import com.codeacademy.diningreview.model.Review;
import com.codeacademy.diningreview.model.User;
import com.codeacademy.diningreview.repositories.RestaurantRepository;
import com.codeacademy.diningreview.repositories.ReviewRepository;
import com.codeacademy.diningreview.repositories.UserRepository;
import org.hibernate.QueryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("review/api/v1")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public RestaurantController(RestaurantRepository restaurantRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/Restaurants/{id}") //get all
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") Long id){
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if (restaurantOptional.isPresent()){

            Restaurant restaurant = restaurantOptional.get();

            if (restaurant.getPeanut() != null){
                restaurant.setPeanut(Math.round(restaurant.getPeanut() * 100.0) / 100.0);
            }
           if (restaurant.getDiary() != null){
               restaurant.setDiary(Math.round(restaurant.getDiary() * 100.0) / 100.0);
           }
           if (restaurant.getEgg() != null){
               restaurant.setEgg(Math.round(restaurant.getEgg() * 100.0) / 100.0);
           }

            return  ResponseEntity.ok(restaurant);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/search")
    public List<Restaurant> searchRestaurant(@RequestParam Integer zipcode,
                                             @RequestParam (required = false) String peanut,
                                             @RequestParam (required = false) String diary,
                                             @RequestParam (required = false) String eggs
                                                 ) throws QueryException {

        if (zipcode == null)  { // Handle the case where zipcode is null
            return new ArrayList<>();
        }

        if (peanut != null) {
            if (diary != null && eggs != null){
                return restaurantRepository.findByZipCodeOrderPeanutAndDiaryAndEggsByDesc();
            } else if (diary != null ) {
                return restaurantRepository.findByZipCodeOrderPeanutAndDiaryByDesc();
            } else if (eggs != null) {
                return restaurantRepository.findByZipCodeOrderPeanutAndEggsByDesc();
            } else {
                return restaurantRepository.findByZipCodeOrderPeanutByDesc(zipcode);
            }
        } else if (diary != null) {
            if (eggs != null){
                return restaurantRepository.findByZipCodeOrderDiaryAndEggsByDesc();
            }
            else {
                return restaurantRepository.findByZipCodeOrderDiaryByDesc(zipcode);
            }
        } else if (eggs != null) {
            return restaurantRepository.findByZipCodeOrderEggByDesc(zipcode);
        } else {
            throw new QueryException("At least one parameter (peanut, diary, eggs) is required.");
        }
    }

}

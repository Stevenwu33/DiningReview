package com.codeacademy.diningreview.controller;

import com.codeacademy.diningreview.model.User;
import com.codeacademy.diningreview.repositories.RestaurantRepository;
import com.codeacademy.diningreview.repositories.ReviewRepository;
import com.codeacademy.diningreview.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping
public class UserController {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public UserController(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }
    public User registerUser(@RequestBody User user){
        User newUser = userRepository.save(user);

        return newUser;
    }

    @GetMapping
    public Iterable<User> getUser(){
        return userRepository.findAll();
    }

    @PutMapping({"/id"})
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updateUser){
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()){
            return null;
        }

        User userToUpdate = userOptional.get();
        userToUpdate.setCity(updateUser.getCity());
        userToUpdate.setState(updateUser.getState());
        userToUpdate.setZipCode(userToUpdate.getZipCode());
        userToUpdate.setPeanutAllergy(userToUpdate.getPeanutAllergy());
        userToUpdate.setDiaryAllergy(userToUpdate.getDiaryAllergy());
        userToUpdate.setEggAllergy(updateUser.getEggAllergy());


        User updatedUser = userRepository.save(userToUpdate);

        return updatedUser;
    }
}

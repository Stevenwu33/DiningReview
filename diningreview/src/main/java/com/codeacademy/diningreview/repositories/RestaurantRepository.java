package com.codeacademy.diningreview.repositories;

import com.codeacademy.diningreview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {

    List<Restaurant> findByZipcodeAndRestaurantName(int zipcode, String restaurantName);
    List<Restaurant> findByZipCodeOrderPeanutByDesc(int zipcode);
    List<Restaurant> findByZipCodeOrderDiaryByDesc(int zipcode);
    List<Restaurant> findByZipCodeOrderEggByDesc(int zipcode);



    Optional<Restaurant> findById(Long id);

    Iterable<Restaurant> findByZipcode(Integer zipcode);

    List<Restaurant> findByZipCodeOrderPeanutAndDiaryAndEggsByDesc();

    List<Restaurant> findByZipCodeOrderPeanutAndDiaryByDesc();

    List<Restaurant> findByZipCodeOrderDiaryAndEggsByDesc();

    List<Restaurant> findByZipCodeOrderPeanutAndEggsByDesc();
}

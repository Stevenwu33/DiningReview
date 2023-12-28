package com.codeacademy.diningreview.controller;

import com.codeacademy.diningreview.model.Restaurant;
import com.codeacademy.diningreview.model.Review;
import com.codeacademy.diningreview.model.ReviewStatus;
import com.codeacademy.diningreview.repositories.RestaurantRepository;
import com.codeacademy.diningreview.repositories.ReviewRepository;
import com.codeacademy.diningreview.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class ReviewController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public ReviewController(RestaurantRepository restaurantRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/Reviews")
    public Iterable<Review> getAllReview(){
        return reviewRepository.findAll();
    }
    @GetMapping("/reviews-approved")
    public Iterable<Review> getApprovedReviews(){
        return reviewRepository.findByReviewStatus(ReviewStatus.ACCEPTED);
    }
    @GetMapping("/reviews-pending")
    public Iterable<Review> getPendingReviews(){
        return reviewRepository.findByReviewStatus(ReviewStatus.PENDING);
    }

    @GetMapping("/reviews-rejected")
    public Iterable<Review> getRejectedReviews(){
        return reviewRepository.findByReviewStatus(ReviewStatus.REJECTED);
    }


    @PostMapping("/{restaurantId}/review")
    public ResponseEntity<String> createReview(@PathVariable ("restaurantId") Long restaurantId, @RequestBody Review review){
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if (restaurantOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Restaurant Not Found");
        }

        Restaurant restaurant = restaurantOptional.get();
        review.setRestaurant(restaurant);
        review.setReviewStatus(ReviewStatus.PENDING);  //all new reviews are under pending after submitted
        reviewRepository.save(review);

        return ResponseEntity.status(HttpStatus.OK).body("Review Saved");
    }
    @PutMapping("/admin-review/{id}")
    public Review approveReview(@PathVariable("id") Long id){
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (reviewOptional.isEmpty()){
            return null;
        }

        Review reviewToApprove = reviewOptional.get();
        reviewToApprove.setReviewStatus(ReviewStatus.ACCEPTED);
        Review reviewApproved = reviewRepository.save(reviewToApprove);

        return reviewApproved;
    }

    @PutMapping
    public Review rejectReview(@PathVariable ("id") Long id){
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (reviewOptional.isEmpty()){
            return null;
        }

        Review reviewToReject = reviewOptional.get();
        reviewToReject.setReviewStatus(ReviewStatus.REJECTED);
        Review rejectedReview = reviewRepository.save(reviewToReject);

        return rejectedReview;

    }
}

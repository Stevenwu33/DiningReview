package com.codeacademy.diningreview.repositories;

import com.codeacademy.diningreview.model.Review;

import com.codeacademy.diningreview.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {

    List<Review> findByStatus(Enum status);
    Iterable<Review> findByReviewStatus(ReviewStatus status);
}

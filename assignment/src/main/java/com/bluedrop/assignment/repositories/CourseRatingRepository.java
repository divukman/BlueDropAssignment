package com.bluedrop.assignment.repositories;


import com.bluedrop.assignment.entities.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRatingRepository extends JpaRepository<CourseRating, Long> {
}

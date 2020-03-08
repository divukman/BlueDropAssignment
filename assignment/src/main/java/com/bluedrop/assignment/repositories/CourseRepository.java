package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.Course;
import com.bluedrop.assignment.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

package com.bluedrop.assignment.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRating {
    @EmbeddedId
    CourseRatingKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    Course course;

    int rating;
}

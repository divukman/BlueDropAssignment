package com.bluedrop.assignment.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRatingKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "course_id")
    Long courseId;
}

package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

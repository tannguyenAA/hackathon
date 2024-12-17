package com.example.demo.repository;

import com.example.demo.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepo extends JpaRepository<Field, String> {
    List<Field> findByUserId(String userId);
}

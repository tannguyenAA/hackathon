package com.example.demo.repository;

import com.example.demo.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileRepo extends JpaRepository<MediaFile, String> {
}

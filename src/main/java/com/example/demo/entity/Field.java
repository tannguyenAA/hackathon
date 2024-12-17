package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
    private String fieldName;
    private String city;
    private String district;
    private String ward;
    private String address;
    private String description;
    @OneToMany(mappedBy = "field", fetch = FetchType.EAGER)
    private List<FiledService> service;
    @OneToMany(mappedBy = "field", fetch = FetchType.EAGER)
    private List<MediaFile> mediaFiles;
}

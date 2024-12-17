package com.example.demo.controller.request;

import com.example.demo.entityenum.FieldServiceEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldCreateCommand {
    private String fieldName;
    private String address;
    private String description;
    private String city;
    private String district;
    private String ward;
    private List<FieldServiceEnum> service;
}

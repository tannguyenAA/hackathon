package com.example.demo.entity;

import com.example.demo.entityenum.FieldServiceEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiledService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private FieldServiceEnum filedService;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fieldId")
    @Getter(AccessLevel.NONE)
    @JsonIgnoreProperties
    private Field field;

    public Field getField() {
        return null;
    }
}

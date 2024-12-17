package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fileType;
    private String fileUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fieldId")
    @Getter(AccessLevel.NONE)
    private Field field;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pitchId")
    @Getter(AccessLevel.NONE)
    private Pitch pitch;

    public Field getField() {
        return null;
    }
    public Pitch getPitch() {
        return null;
    }
}

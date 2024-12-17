package com.example.demo.entity;

import com.example.demo.entityenum.GrassTypeEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
public class Pitch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "pitch", fetch = FetchType.EAGER)
    private List<PitchType> pitchTypes;
    @OneToMany(mappedBy = "pitch", fetch = FetchType.EAGER)
    private List<MediaFile> mediaFiles;
    @OneToMany(mappedBy = "pitch", fetch = FetchType.EAGER)
    private List<PitchTimeSlot> pitchTimeSlots;
    @Enumerated(EnumType.STRING)
    private GrassTypeEnum grassTypeEnum;
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @JoinColumn(name = "fieldId")
    private Field field;
    private String userId;

    public Field getField() {
        return null;
    }
}

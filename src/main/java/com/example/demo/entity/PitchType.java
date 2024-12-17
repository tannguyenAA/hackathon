package com.example.demo.entity;

import com.example.demo.entityenum.PitchTypeEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class PitchType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private PitchTypeEnum pitchType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pitchId")
    @Getter(AccessLevel.NONE)
    private Pitch pitch;

    public Pitch getPitch() {
        return null;
    }
}

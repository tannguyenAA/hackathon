package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Data
public class PitchTimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int startHour;
    private int endHour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pitchId")
    @Getter(AccessLevel.NONE)
    private Pitch pitch;
    private BigDecimal price;

    public Pitch getPitch() {
        return null;
    }
}

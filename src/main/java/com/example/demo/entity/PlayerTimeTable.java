package com.example.demo.entity;

import com.example.demo.entityenum.DateOfWeek;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class PlayerTimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerGameInfoId")
    @Getter(AccessLevel.NONE)
    private PlayerGameInfo playerGameInfo;
    @Enumerated(EnumType.STRING)
    private DateOfWeek dateOfWeek;
    private int startHour;
    private int endHour;
    @Transient
    private int dateOfWeekIndex;

    public PlayerGameInfo getPlayerGameInfo() {
        return null;
    }
}

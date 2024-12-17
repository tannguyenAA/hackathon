package com.example.demo.entity;

import com.example.demo.entityenum.*;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String userId;
    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field field;
    @ManyToOne
    @JoinColumn(name = "pitchId")
    private Pitch pitch;
    @ManyToOne
    @JoinColumn(name = "timeSlotId")
    private PitchTimeSlot timeSlot;
    @Enumerated(EnumType.STRING)
    private GameModeEnum gameModeEnum;
    @Enumerated(EnumType.STRING)
    private RefereeType refereeType;
    private Integer maxOfPlayers;
    private Integer remainingSlot;
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<GameRegisteredUser> players;
    private String date;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    @Enumerated(EnumType.STRING)
    private PitchTypeEnum pitchType;
    private BigDecimal price;
    private DateOfWeek dateOfWeek;
    private Integer dateOfMonth;
    private String imageUrl;
}

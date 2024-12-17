package com.example.demo.entity;

import com.example.demo.entityenum.PitchTypeEnum;
import com.example.demo.entityenum.PlayerTypeEnum;
import com.example.demo.entityenum.PositionEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class PlayerType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerInfoId")
    @Getter(AccessLevel.NONE)
    private PlayerGameInfo playerGameInfo ;
    @Enumerated(EnumType.STRING)
    private PlayerTypeEnum playerType;
    @Enumerated(EnumType.STRING)
    private PitchTypeEnum pitchType;
    @Enumerated(EnumType.STRING)
    private PositionEnum position;

    public PlayerGameInfo getPlayerGameInfo() {
        return null;
    }
}

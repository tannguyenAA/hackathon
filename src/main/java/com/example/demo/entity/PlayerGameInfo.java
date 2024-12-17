package com.example.demo.entity;

import com.example.demo.entityenum.PitchTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerGameInfo {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Getter(AccessLevel.NONE)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playerGameInfo")
    private List<PlayerTimeTable> playerTimeTables;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playerGameInfo")
    private List<PlayerType> playerTypes;
    @Enumerated(EnumType.STRING)
    private PitchTypeEnum preferGameType;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playerGameInfo")
    private List<Location> preferLocations;
    private BigDecimal preferPriceStart;
    private BigDecimal preferPriceEnd;

    public User getUser() {
        return null;
    }
}

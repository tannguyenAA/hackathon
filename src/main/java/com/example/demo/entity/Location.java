package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String city;
    private String district;
    private String ward;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerGameInfoId")
    @Getter(AccessLevel.NONE)
    private PlayerGameInfo playerGameInfo;

    public PlayerGameInfo getPlayerGameInfo() {
        return null;
    }
}

package com.example.demo.controller.request;

import com.example.demo.entity.PitchType;
import com.example.demo.entityenum.GameModeEnum;
import com.example.demo.entityenum.PitchTypeEnum;
import com.example.demo.entityenum.RefereeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameCreateCommand {
    private String name;
    private String description;
    private String timeSlotId;
    private String pitchId;
    private String fieldId;
    private GameModeEnum gameModeEnum;
    private RefereeType refereeType;
    private Integer maxOfPlayers;
    private String date;
    private PitchTypeEnum pitchType;
    private BigDecimal price;
}

package com.example.demo.controller.request;

import com.example.demo.entity.PitchTimeSlot;
import com.example.demo.entityenum.GrassTypeEnum;
import com.example.demo.entityenum.PitchTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PitchCreateCommand {
    private String name;
    private String description;
    private List<PitchTypeEnum> pitchTypes;
    private List<PitchTimeSlot> pitchTimeSlots;
    private GrassTypeEnum grassTypeEnum;
}

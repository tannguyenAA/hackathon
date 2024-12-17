package com.example.demo.controller.request;

import com.example.demo.entityenum.PitchTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SearchGameCommand {
    private String city;
    private String district;
    private String ward;
    private PitchTypeEnum pitchTypeEnum;
    private String date;
    private Integer startHour;
    private Integer endHour;
    private BigDecimal preferPriceStart;
    private BigDecimal preferPriceEnd;
}

package com.example.demo.controller.request;

import com.example.demo.entity.Location;
import com.example.demo.entity.PlayerTimeTable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class MatchGameCommand {
    private List<PlayerTimeTable> playerTimeTables;
    private Location preferLocations;
    private BigDecimal preferPriceStart;
    private BigDecimal preferPriceEnd;
}

package com.example.demo.controller.request;

import com.example.demo.entity.PaymentCard;
import com.example.demo.entity.PlayerGameInfo;
import com.example.demo.entityenum.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterCommand {
    private String phoneNumber;
    private UserType userType;
    private String name;
    private Integer age;
    private String email;
    private List<PaymentCard> paymentCards;
    private PlayerGameInfo playerGameInfo;
}

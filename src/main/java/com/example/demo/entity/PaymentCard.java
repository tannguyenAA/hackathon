package com.example.demo.entity;

import com.example.demo.entityenum.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class PaymentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Getter(AccessLevel.NONE)
    private User user;
    private String name;
    private String number;
    private String expiryDate;
    private String cvv;
    private CardType cardType;

    public User getUser() {
        return null;
    }
}

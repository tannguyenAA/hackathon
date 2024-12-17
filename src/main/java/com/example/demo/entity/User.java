package com.example.demo.entity;

import com.example.demo.entityenum.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String phoneNumber;
    private String verificationCode;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String password;
    // player info
    private String name;
    private Integer age;
    private String email;
    // field owner info
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PaymentCard> paymentCards;

    @OneToOne(mappedBy = "user")
    private PlayerGameInfo playerGameInfo;

}

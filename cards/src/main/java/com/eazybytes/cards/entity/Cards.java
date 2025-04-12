package com.eazybytes.cards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id")
    private Long cardId;
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="card_number")
    private String cardNumber;
    @Column(name="card_type")
    private String cardType;
    @Column(name="total_limit")
    private long totalLimit;
    @Column(name="amount_used")
    private long amountUsed;
    @Column(name="available_amount")
    private long availableAmount;

}

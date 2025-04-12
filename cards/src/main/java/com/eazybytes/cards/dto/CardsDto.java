package com.eazybytes.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @NotEmpty(message = "Card must not be empty")
    private Long cardId;
    @NotEmpty(message = "Mobile Number must not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be a 10 digit number")
    private String mobileNumber;
    @NotEmpty(message = "Card Number must not be empty")
    private String cardNumber;
    @NotEmpty(message = "Card Type must not be empty")
    private String cardType;
    @Positive(message = "Total Limit must be positive")
    private long totalLimit;
    @PositiveOrZero(message = "Amount used must be Zero or more than")
    private long amountUsed;
    @PositiveOrZero(message = "Available amount must be Zero or more than")
    private long availableAmount;
}

package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "Loan number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{7})", message = "Loan number must be 7 digits")
    private String loanNumber;
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @NotEmpty(message = "Loan type can not be null or empty")
    private String loanType;
    @PositiveOrZero(message = "Total Loan Amount should be equal or greater than zero")
    private int totalLoan;
    @PositiveOrZero(message = "Amount Paid should be equal or greater than zero")
    private int amountPaid;
    @PositiveOrZero(message = "Outstanding Amount should be equal or greater than zero")
    private int outStandingAmount;
}

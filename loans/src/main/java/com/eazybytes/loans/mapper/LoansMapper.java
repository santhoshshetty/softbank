package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoanDto(Loans loans, LoansDto loansDto){
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutStandingAmount(loans.getOutStandingAmount());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans){
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setOutStandingAmount(loansDto.getOutStandingAmount());
        loans.setAmountPaid(loansDto.getAmountPaid());
        return loans;
    }
}

package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber){
        Optional<Loans> loan=loansRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent())
           throw new LoanAlreadyExistsException("Loan already registered with the mobile number: "+mobileNumber);
        loansRepository.save(createNewLoan(mobileNumber));
    }

    public Loans createNewLoan(String mobileNumber){
        Loans newLoan=new Loans();
        long randomLoanNumber=1000000L + new Random().nextInt(90000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setLoanType("HOME LOAN");
        newLoan.setTotalLoan(5_00_000);
        newLoan.setAmountPaid(0);
        newLoan.setOutStandingAmount(5_00_000);
        newLoan.setMobileNumber(mobileNumber);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoanDetails(String mobileNumber){
        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber)
        );
        return LoansMapper.mapToLoanDto(loans,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto){
        Loans loans=loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loan","Loan Number",loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber){
        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}

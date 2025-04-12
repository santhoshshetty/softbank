package com.eazybytes.loans.controller;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansContactInfoDto;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class LoansController {

    private ILoansService iLoansService;

    @Autowired
    private LoansContactInfoDto loansApplicationInfoDto;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",
                                                              message = "Mobile number must be 10 digits")
                                                      String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam
                                                     @Pattern(regexp = "^$|[0-9]{10}",
                                                             message = "Mobile number must be 10 digits")
                                                     String mobileNumber){
        LoansDto loansDto=iLoansService.fetchLoanDetails(mobileNumber);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(loansDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoansDto loansDto){
        boolean isUpdated= iLoansService.updateLoan(loansDto);
        if(!isUpdated)
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoansConstants.STATUS_500, LoansConstants.MESSAGE_500));
        else
            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam
                                                      @Pattern(regexp = "^$|[0-9]{10}",
                                                              message = "Mobile Number must be 10 digits")
                                                      String mobileNumber){
        boolean isDeleted= iLoansService.deleteLoan(mobileNumber);
        if(isDeleted)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoansConstants.STATUS_500, LoansConstants.MESSAGE_500));
    }

    @GetMapping("/contactInfo")
    public ResponseEntity<LoansContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansApplicationInfoDto);
    }

}

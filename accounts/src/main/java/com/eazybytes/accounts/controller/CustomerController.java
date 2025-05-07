package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name="REST APIs for Customers in Soft Bank",
        description="REST APIs for Customer in Soft Bank - Create Read Update Delete"
)
@RestController
@RequestMapping(path="/api",produces={MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    public CustomerController(ICustomerService iCustomerService){
        this.iCustomerService=iCustomerService;
    }

    private final ICustomerService iCustomerService;

    @Operation(summary = "Fetch Customer Details REST API")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS - CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP STATUS - INTERNAL SERVER ERROR",
                    content=@Content(
                            schema=@Schema(implementation = ErrorResponseDto.class)
                    )
            )})
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits") String mobileNumber){
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}

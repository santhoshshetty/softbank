package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(
        name="CRUD REST APIs for accounts in Eazy Bank",
        description="CRUD REST APIs for accounts in Eazy Bank - Create Read Update Delete"
)
@RestController
@RequestMapping(path="/api",produces={MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    //Whenever there is a single constructor then there is no need of mentioning @Autowired
    //anywhere inside the constructor or class
    private final IAccountService accountService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    public AccountsController(IAccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Account REST API")
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS - CREATED"
    )
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customer){
        accountService.createAccount(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNumber){
        CustomerDto customerDto=accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PatchMapping("/update")
    @Operation(summary = "Update Account REST API")
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
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customer){
        boolean isUpdated=accountService.updateAccount(customer);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNumber){
        boolean isDeleted=accountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @GetMapping("/buildInfo")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/envInfo")
    public ResponseEntity<String> getEnvironmentInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contactInfo")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}

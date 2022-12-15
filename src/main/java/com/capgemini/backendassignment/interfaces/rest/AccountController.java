package com.capgemini.backendassignment.interfaces.rest;

import com.capgemini.backendassignment.application.AccountOpenRequestHandler;
import com.capgemini.backendassignment.application.AccountService;
import com.capgemini.backendassignment.infrastructure.dto.AccountOpenRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequestMapping("account")
@RestController
@RequiredArgsConstructor
@Tag(name = "Open an Account")
public class AccountController {

    private final AccountOpenRequestHandler requestHandler;

    @PostMapping(value = "openAccount", consumes = "application/json")
    @Operation(summary = "Open an account for a customer with given credit")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description =
                                    "The account is opened successfully.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "An error occurred while adding the account.",
                            content = @Content)
            })
    public ResponseEntity<String> openAccount(
            @Valid @RequestBody final AccountOpenRequestDto accountOpenRequestDto) {
        try {
            requestHandler.openAccount(accountOpenRequestDto);
            return ResponseEntity.ok().body("The account is opened successfully.");
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while adding the account.");
        }
    }
}

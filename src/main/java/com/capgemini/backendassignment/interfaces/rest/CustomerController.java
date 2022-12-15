package com.capgemini.backendassignment.interfaces.rest;

import com.capgemini.backendassignment.application.CustomerService;
import com.capgemini.backendassignment.infrastructure.dto.CustomerDto;
import com.capgemini.backendassignment.infrastructure.exception.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequestMapping("customer")
@RestController
@RequiredArgsConstructor
@Tag(name = "Customer Details")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "getCustomer/{customer_id}")
    @Operation(summary = "Get customer details for a given customerId")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer details returned successfully.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CustomerDto.class))
                            }),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid customer id supplied.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invalid request",
                            content = @Content)
            })
    public CustomerDto getCustomerDetails(
            @Valid @PathVariable("customer_id") final Long customerId) throws CustomerNotFoundException {
        try {
            return customerService.getCustomerDetails(customerId);
        } catch (final Exception e) {
            throw new CustomerNotFoundException(e.getMessage());
        }
    }
}

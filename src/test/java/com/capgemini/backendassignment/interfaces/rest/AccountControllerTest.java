package com.capgemini.backendassignment.interfaces.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.backendassignment.application.AccountOpenRequestHandler;
import com.capgemini.backendassignment.infrastructure.dto.AccountOpenRequestDto;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.List;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean private AccountOpenRequestHandler requestHandler;

    @Autowired private MockMvc mockMvc;

    @Test
    void openAccount() throws Exception {

        final AccountOpenRequestDto accountOpenRequest = AccountOpenRequestDto.builder()
                .customerId(1L)
                .initialCredit(500L)
                .build();
        final String content = objectMapper.writeValueAsString(accountOpenRequest);

        final Customer customer = aCustomer();

        final MockHttpServletRequestBuilder requestBuilder =
                post("/account/openAccount")
                        .contentType("application/json")
                        .content(content)
                        .accept("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(requestHandler, times(1)).openAccount(accountOpenRequest);
    }

    private Customer aCustomer() {
        return Customer
                .builder()
                .customerId(1L)
                .name("name")
                .surname("surname")
                .accounts(List.of())
                .transactions(List.of())
                .build();
    }
}
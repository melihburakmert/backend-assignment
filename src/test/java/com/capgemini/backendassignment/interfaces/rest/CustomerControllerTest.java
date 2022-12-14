package com.capgemini.backendassignment.interfaces.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.capgemini.backendassignment.application.CustomerService;
import com.capgemini.backendassignment.infrastructure.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.List;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    public static final long CUSTOMER_ID = 1L;
    @MockBean private CustomerService customerService;

    @Autowired private MockMvc mockMvc;

    private static MockHttpServletRequestBuilder getCustomerDetailsRequestBuilder(
            final Long customerId) {
        return get("/customer/getCustomer/{customer_id}", customerId.toString())
                .accept("application/json");
    }

    @Test
    void getCustomerDetails() throws Exception {

        final CustomerDto customerDto = aCustomerDto(CUSTOMER_ID);
        when(customerService.getCustomerDetails(CUSTOMER_ID)).thenReturn(customerDto);

        final MockHttpServletRequestBuilder requestBuilder =
                getCustomerDetailsRequestBuilder(CUSTOMER_ID);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    private CustomerDto aCustomerDto(final long customerId) {
        return CustomerDto.builder()
                .customerId(customerId)
                .name("name")
                .surname("surname")
                .balance(500L)
                .transactions(List.of())
                .accounts(List.of())
                .build();
    }
}
package com.capgemini.backendassignment.infrastructure.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class CustomerDto {

    private Long customerId;
    @NotNull private String name;
    @NotNull private String surname;
    @NotNull private long balance;
    @NotNull private List<Long> transactions;
    @NotNull private List<Long> accounts;
}

package com.jwal.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.Nullable;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MutationRequest(
        @NotBlank String accountId,
        @NotBlank String operationId,
        @Positive BigInteger amount,
        @Nullable String reason
) {
}

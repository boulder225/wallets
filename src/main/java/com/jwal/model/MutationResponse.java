package com.jwal.model;

import java.math.BigInteger;
import java.time.Instant;

public record MutationResponse(
    String id,
    String operationId,
    String accountId,
    BigInteger amount,
    Instant ts
) {

}

package com.jwal.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigInteger;

public record Account(
        String id,
        String name,
        @JsonSerialize(using = ToStringSerializer.class)BigInteger available
        ) {
    public Account withAvailable(BigInteger available) {
        return new Account(id, name, available);
    }

}

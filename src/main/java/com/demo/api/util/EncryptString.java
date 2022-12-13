package com.demo.api.util;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EncryptString {
    @NotNull
    String value;
}

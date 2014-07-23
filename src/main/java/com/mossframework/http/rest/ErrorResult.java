package com.mossframework.http.rest;

import lombok.Data;

@Data
public class ErrorResult {
    Integer root;
    Integer sub;
    Integer code;
    String message;
}

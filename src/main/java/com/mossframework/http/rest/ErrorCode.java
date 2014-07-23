package com.mossframework.http.rest;

import java.util.Map;

import com.mossframework.code.util.CodeEnumUtils;

public enum ErrorCode {
    Error1(1),
    Error2(2),
    ;
    static Map<Integer, ErrorCode> map = CodeEnumUtils.getCodeMap(ErrorCode.class);
    
    ErrorCode(int code) {
        this.code = code;
    }
    
    private int code;
    
    public int getCode() {
        return code;
    }
    
    public static ErrorCode getEnum(int code) {
        return map.get(code);
    }
}

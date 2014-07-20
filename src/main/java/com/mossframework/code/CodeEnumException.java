package com.mossframework.code;

/**
 * CodeEnum 처리관련 Exception
 * @author moss
 *
 */
public class CodeEnumException extends RuntimeException {
    private static final long serialVersionUID = 1726445749533968765L;
    
    public CodeEnumException(String message, Throwable cause) {
        super(message, cause);
    }

}

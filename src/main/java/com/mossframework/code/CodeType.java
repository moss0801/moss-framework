package com.mossframework.code;



public enum CodeType {
    Code(int.class, "getCode", "getEnum"),
    StringCode(String.class, "getStringCode", "getEnum"),
    LongCode(long.class, "getLongCode", "getEnumFromLongCode");
    
    CodeType(Class<?> type, String getCodeMethodName, String getEnumMethodName) {
        this.type = type;
        this.getCodeMethodName = getCodeMethodName;
        this.getEnumMethodName = getEnumMethodName;
    }
    
    private Class<?> type;
    private String getCodeMethodName;
    private String getEnumMethodName;
    
    public Class<?> getType() {
        return type;
    }

    public String getGetCodeMethodName() {
        return getCodeMethodName;
    }
    
    public String getGetEnumMethodName() {
        return getEnumMethodName;
    }
}

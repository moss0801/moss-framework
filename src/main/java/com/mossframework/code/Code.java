package com.mossframework.code;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Code {
    
    Convert[] value() default {};
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface Convert {
        
        ConvertTarget target();
        
        CodeType type();
    }
}

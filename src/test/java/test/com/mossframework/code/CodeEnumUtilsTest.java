package test.com.mossframework.code;

import org.junit.Assert;
import org.junit.Test;

import com.mossframework.code.CodeEnumUtils;

public class CodeEnumUtilsTest {
    
    public enum CodeEnum {
        First(1, "first", 3),
        Second(2, "second", 4);
        
        CodeEnum(int code, String stringCode, long longCode) {
            this.code = code;
            this.stringCode = stringCode;
            this.longCode = longCode;
        }
        
        private int code;
        private String stringCode;
        private long longCode;
        
        public int getCode() {
            return code;
        }
        
        public String getStringCode() {
            return stringCode;
        }
        
        public long getLongCode() {
            return longCode;
        }
    }
    
    @Test
    public void getCode() {
        // Act
        int code = CodeEnumUtils.getCode(CodeEnum.First);
        
        // Assert
        Assert.assertEquals(CodeEnum.First.getCode(), code);
    }
    
    @Test
    public void getEnum() {
        // Arrange
        int code = CodeEnum.First.getCode();
        
        // Act
        CodeEnum value = CodeEnumUtils.getEnum(CodeEnum.class, code);
        
        // Assert
        Assert.assertEquals(CodeEnum.First, value);
    }
    
    @Test
    public void getStringCode() {
        // Act
        String stringCode = CodeEnumUtils.getStringCode(CodeEnum.First);
        
        // Assert
        Assert.assertEquals(CodeEnum.First.getStringCode(), stringCode);
    }
    
    @Test
    public void getEnumFromStringCode() {
        // Arragne
        String stringCode = CodeEnum.Second.getStringCode();
        
        // Act
        CodeEnum value = CodeEnumUtils.getEnum(CodeEnum.class, stringCode);
        
        // Assert
        Assert.assertEquals(CodeEnum.Second, value);
    }
    
    @Test
    public void getLongCode() {
        // Act
        long longCode = CodeEnumUtils.getLongCode(CodeEnum.Second);
        
        // Assert
        Assert.assertEquals(CodeEnum.Second.getLongCode(), longCode);
    }
    
    @Test
    public void getEnumFromLongCode() {
        // Arrange
        long longCode = CodeEnum.Second.getLongCode();
        
        // Act
        CodeEnum value = CodeEnumUtils.getEnumFromLongCode(CodeEnum.class, longCode);
        
        // Assert
        Assert.assertEquals(longCode, value.getLongCode());
    }
}

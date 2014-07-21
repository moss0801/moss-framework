package com.mossframework.code.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.mossframework.beans.PropertyUtils;
import com.mossframework.code.Code;
import com.mossframework.code.CodeEnumException;
import com.mossframework.code.CodeType;
import com.mossframework.code.ConvertTarget;

/**
 * Code를 Enum으로 관리하기 위한 유틸
 * @author moss
 *
 */
public class CodeEnumUtils {
    public static final String PROPERTY_CODE = "code";
    public static final String PROPERTY_STRING_CODE = "stringCode";
    public static final String PROPERTY_LONG_CODE = "longCode";
    
    @SuppressWarnings("unchecked")
    public static <T> Map<Integer, T> getCodeMap(Class<T> enumClass) {
        Map<Integer, T> codeMap = new HashMap<Integer, T>();
        Method getCodeMethod = findCodeMethod(enumClass, CodeType.Code);
        if (null == getCodeMethod)
            return codeMap;
        
        for (Object value : enumClass.getEnumConstants()) {
            try {
                codeMap.put((Integer)getCodeMethod.invoke(value), (T) value);
            } catch (Exception e) { }
        }
        return codeMap;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> getStringCodeMap(Class<T> enumClass) {
        Map<String, T> codeMap = new HashMap<String, T>();
        Method getCodeMethod = findCodeMethod(enumClass, CodeType.StringCode);
        if (null == getCodeMethod)
            return codeMap;
        
        for (Object value : enumClass.getEnumConstants()) {
            try {
                codeMap.put((String)getCodeMethod.invoke(value), (T) value);
            } catch (Exception e) { }
        }
        return codeMap;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Map<Long, T> getLongCodeMap(Class<T> enumClass) {
        Map<Long, T> codeMap = new HashMap<Long, T>();
        Method getCodeMethod = findCodeMethod(enumClass, CodeType.LongCode);
        if (null == getCodeMethod)
            return codeMap;
        
        for (Object value : enumClass.getEnumConstants()) {
            try {
                codeMap.put((Long)getCodeMethod.invoke(value), (T) value);
            } catch (Exception e) { }
        }
        return codeMap;
    }
    
    /**
     * Enum의 code값을 조회합니다.
     */
    public static int getCode(Enum<?> value) {
        try {
            return (int) PropertyUtils.getSimpleProperty(value, PROPERTY_CODE);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CodeEnumException("fail to get code from " + (null == value ? null : value.getClass()), e);
        }
    }
    
    /**
     * Enum의 code와 일치하는 value를 반환합니다.<br />
     * code값에 해당하는 value가 없는 경우 null이 반환됩니다.
     * @param <E>
     */
    public static <E> E getEnum(Class<E> enumClass, int code) {
        if (!enumClass.isEnum())
            return null;
        
        for (E value : enumClass.getEnumConstants()) {
            if (getCode((Enum<?>) value) == code)
                return (E) value;
        }
        return null;
    }
    
    /**
     * Enum의 code와 일치하는 value를 반환합니다.<br />
     * code값에 해당하는 value가 없는 경우 null이 반환됩니다.
     * @param <E>
     */
    public static <E> E getEnumWithMethod(Class<E> enumClass, int code) {
        if (!enumClass.isEnum())
            return null;
        Method method = BeanUtils.findMethod(enumClass, "getcode");
        for (E value : enumClass.getEnumConstants()) {
            try {
                if ((int) method.invoke(null, value) == code)
                    return (E) value;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public static <E> E getEnumWithGetEnumMethod(Class<E> enumClass, Method getEnumMethod, int code) {
        if (!enumClass.isEnum())
            return null;
        try {
            return (E) getEnumMethod.invoke(null, code);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Enum의 code와 일치하는 value를 반환합니다.<br />
     * code값에 해당하는 value가 없는 경우 null이 반환됩니다.
     * @param <E>
     */
    public static <E> E getEnumWithGetCodeMethod(Class<E> enumClass, Method getCodeMethod, int code) {
        for (E value : enumClass.getEnumConstants()) {
            try {
                if ((int) getCodeMethod.invoke(value) == code)
                    return (E) value;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Enum의 stringCode를 조회합니다.
     */
    public static String getStringCode(Enum<?> value) {
        try {
            return (String) PropertyUtils.getProperty(value, PROPERTY_STRING_CODE);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CodeEnumException("fail to get stringCode from " + (null == value ? null : value.getClass()), e);
        }
    }
    
    /**
     * Enum의 stringCode와 일치하는 value를 반환합니다.<br />
     * stringCode에 해당하는 value가 없는 경우 null이 반환됩니다.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String stringCode) {
        if (null == stringCode)
            return null;
        for (Enum<?> value : enumClass.getEnumConstants()) {
            if (stringCode.equals(getStringCode(value)))
                return (E) value;
        }
        return null;
    }
    
    /**
     * Enum의 longCode값을 조회합니다.
     */
    public static long getLongCode(Enum<?> value) {
        try {
            return (long) PropertyUtils.getProperty(value, PROPERTY_LONG_CODE);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CodeEnumException("fail to get longCode from " + (null == value ? null : value.getClass()), e);
        }
    }
    
    /**
     * Enum의 longCode와 일치하는 value를 반환합니다.<br />
     * longCode값에 해당하는 value가 없는 경우 null이 반환됩니다.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E getEnumFromLongCode(Class<E> enumClass, long longCode) {
        for (Enum<?> value : enumClass.getEnumConstants()) {
            if (getLongCode(value) == longCode)
                return (E) value;
        }
        return null;
    }
    
    /**
     * Enum의 ConvertTarget의 CodeType을 반환합니다. 
     */
    public static <E> CodeType getCodeType(Class<E> enumClass, ConvertTarget convertTarget) {
        if (!enumClass.isEnum())
            return null;
        Code code = enumClass.getAnnotation(Code.class);
        if (null == code)
            return CodeType.Code;
        for (Code.Convert convert : code.value()) {
            if (convert.target() == convertTarget)
                return convert.type();
        }
        return CodeType.Code;
    }
    
    public static <E> Method findCodeMethod(Class<E> enumClass, CodeType codeType) {
        if (null == codeType) {
            codeType = CodeType.Code;
        }
        Method method = BeanUtils.findMethod(enumClass, codeType.getGetCodeMethodName());
        if (null == method)
            throw new IllegalArgumentException(enumClass.getName() + " doesn't have '" + codeType.getGetCodeMethodName() + "' method.");
        return method;
    }
    
    public static <E> Method findGetEnumMethod(Class<E> enumClass, CodeType codeType) {
        if (null == codeType) {
            codeType = CodeType.Code;
        }
        
        Method method = BeanUtils.findMethod(enumClass, codeType.getGetEnumMethodName(), codeType.getType()); 
        if (null == method)
            throw new IllegalArgumentException(enumClass.getName() + " doesn't have '" 
                    + codeType.getGetEnumMethodName() + "(" + codeType.getType().getSimpleName() + ")' method.");
        return method;
    }
    
    public static <E> E getEnum(Class<E> enumClass, CodeType codeType, Object code) {
        return getEnum(findGetEnumMethod(enumClass, codeType), code);
    }
    
    @SuppressWarnings("unchecked")
    public static <E> E getEnum(Method getEnumMethod, Object code) {
        try {
            return (E) getEnumMethod.invoke(null, code);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }
}

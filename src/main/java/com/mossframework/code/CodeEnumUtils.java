package com.mossframework.code;

import java.lang.reflect.InvocationTargetException;

import com.mossframework.beans.PropertyUtils;

/**
 * Code를 Enum으로 관리하기 위한 유틸
 * @author moss
 *
 */
public class CodeEnumUtils {
    public static final String PROPERTY_CODE = "code";
    public static final String PROPERTY_STRING_CODE = "stringCode";
    public static final String PROPERTY_LONG_CODE = "longCode";
    
    /**
     * Enum의 code값을 조회합니다.
     */
    public static int getCode(Enum<?> value) {
        try {
            return (int) PropertyUtils.getProperty(value, PROPERTY_CODE);
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
}

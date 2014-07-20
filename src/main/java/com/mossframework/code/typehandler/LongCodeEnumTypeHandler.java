package com.mossframework.code.typehandler;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.BeanUtils;

import com.mossframework.code.util.CodeEnumUtils;

/**
 * LongCode Enum과 DB가 통신하기 위한 TypeHandler
 * @author moss
 */
public class LongCodeEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E>  {
    
    private Class<E> type;
    
    public LongCodeEnumTypeHandler(Class<E> type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        Method codeMethod = BeanUtils.findMethod(type, CodeEnumUtils.PROPERTY_LONG_CODE);
        if (codeMethod == null || codeMethod.getReturnType() != long.class) 
            throw new IllegalArgumentException(type.getSimpleName() + " does not have 'long getLongCode()' method.");
      }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, CodeEnumUtils.getLongCode(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long code = rs.getLong(columnName);
        return CodeEnumUtils.getEnumFromLongCode(type, code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long code = rs.getLong(columnIndex);
        return CodeEnumUtils.getEnumFromLongCode(type, code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long code = cs.getLong(columnIndex);
        return CodeEnumUtils.getEnumFromLongCode(type, code);
    }
} 

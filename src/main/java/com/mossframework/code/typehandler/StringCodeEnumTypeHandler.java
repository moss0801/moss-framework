package com.mossframework.code.typehandler;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.BeanUtils;

import com.mossframework.code.CodeEnumUtils;

/**
 * StringCode Enum과 DB가 통신하기 위한 TypeHandler
 * @author moss
 */
public class StringCodeEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E>  {
    
    private Class<E> type;
    
    public StringCodeEnumTypeHandler(Class<E> type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        Method codeMethod = BeanUtils.findMethod(type, CodeEnumUtils.PROPERTY_STRING_CODE);
        if (codeMethod == null || codeMethod.getReturnType() != String.class) 
            throw new IllegalArgumentException(type.getSimpleName() + " does not have 'String getStringCode()' method.");
      }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, CodeEnumUtils.getStringCode(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String stringCode = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return CodeEnumUtils.getEnum(type, stringCode);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String stringCode = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;
        return CodeEnumUtils.getEnum(type, stringCode);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String stringCode = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return CodeEnumUtils.getEnum(type, stringCode);
    }
} 

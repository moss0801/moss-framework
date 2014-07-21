package test.com.mossframework.db.typehandler;

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
 * Code Enum과 DB가 통신하기 위한 TypeHandler<br />
 * int code만 지원합니다. stinrgCode, longCode등은 지원하지 않습니다.
 * @author moss
 */
public class ReflectionTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E>  {
    
    private Class<E> type;
    
    public ReflectionTypeHandler(Class<E> type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        Method codeMethod = BeanUtils.findMethod(type, "getCode");
        if (codeMethod == null || codeMethod.getReturnType() != int.class) 
            throw new IllegalArgumentException(type.getSimpleName() + " does not have 'int getCode()' method.");
        }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, CodeEnumUtils.getCode(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return CodeEnumUtils.getEnum(type, code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return CodeEnumUtils.getEnum(type, code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return CodeEnumUtils.getEnum(type, code);
    }
} 

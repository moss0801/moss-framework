package test.com.mossframework.db.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import test.com.mossframework.code.RawEnum;

public class RawTypeHandler extends BaseTypeHandler<RawEnum>  {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RawEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public RawEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int  code = rs.getInt(columnName);
        return RawEnum.getEnum(code);
    }

    @Override
    public RawEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int  code = rs.getInt(columnIndex);
        return RawEnum.getEnum(code);
    }

    @Override
    public RawEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int  code = cs.getInt(columnIndex);
        return RawEnum.getEnum(code);
    }
    
} 

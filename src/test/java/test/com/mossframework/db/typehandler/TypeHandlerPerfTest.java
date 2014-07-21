package test.com.mossframework.db.typehandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import test.com.mossframework.code.RawEnum;
import test.com.mossframework.code.ReflectionEnum;

public class TypeHandlerPerfTest {
    
    @Mock
    PreparedStatement ps;
    
    @Mock
    ResultSet rs;
    
    @Before
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(rs.getInt(Mockito.anyInt())).thenReturn(10);
    }
    
    @Test
    public void rawTypeHandler() throws SQLException {
        System.out.println("typeHandlerRaw");
        RawEnum value = RawEnum.Code10;
        RawTypeHandler typeHandler = new RawTypeHandler();
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            typeHandler.setNonNullParameter(ps, i, value, null);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void reflectionTypeHandler() throws SQLException {
        System.out.println("typeHandlerReflection");
        ReflectionEnum value = ReflectionEnum.Code10;
        ReflectionTypeHandler typeHandler = new ReflectionTypeHandler(ReflectionEnum.class);
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            typeHandler.setNonNullParameter(ps, i, value, null);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void rawTypeHandler_ResultSet() throws SQLException {
        System.out.println("typeHandlerRaw_ResultSet");
        RawTypeHandler typeHandler = new RawTypeHandler();
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            typeHandler.getNullableResult(rs, 10);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    
    @Test
    public void reflectionTypeHandler_ResultSet() throws SQLException {
        System.out.println("typeHandlerReflection_ResultSet");
        ReflectionTypeHandler typeHandler = new ReflectionTypeHandler(ReflectionEnum.class);
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++)
        {
            typeHandler.getNullableResult(rs, 10);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}

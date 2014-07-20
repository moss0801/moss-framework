package com.mossframework.data.json.serialize;

import java.io.IOException;
import java.lang.reflect.Method;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mossframework.code.CodeType;
import com.mossframework.code.ConvertTarget;
import com.mossframework.code.util.CodeEnumUtils;

/**
 * Np 통신시 CodeEnum을 처리하기 위한 TypeAdapterFactory
 * @author moss
 *
 */
public class CodeEnumTypeAdapterFactory implements TypeAdapterFactory {
    
    
    private ConvertTarget convertTarget;

    public void setConvertTarget(ConvertTarget convertTarget) {
        this.convertTarget = convertTarget;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (null == convertTarget)
            return null;
        
        @SuppressWarnings("unchecked")
        final Class<T> rawType = (Class<T>) type.getRawType();
        if (!rawType.isEnum()) {
          return null;
        }
        
        final CodeType codeType = CodeEnumUtils.getCodeType(rawType, convertTarget);
        if (CodeType.Code != codeType && CodeType.StringCode != codeType && CodeType.LongCode != codeType) {
            throw new IllegalArgumentException("codeType '" + codeType + "' is not supported.");
        }
        
        final Method getCodeMethod = CodeEnumUtils.findCodeMethod(rawType, codeType);
        final Method getEnumMethod = CodeEnumUtils.findGetEnumMethod(rawType, codeType);
        
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (null == value) {
                    out.nullValue();
                } else if (CodeType.Code == codeType) {
                    out.value(CodeEnumUtils.getCode((Enum<?>) value));
                } else if (CodeType.StringCode == codeType) {
                    out.value(CodeEnumUtils.getStringCode((Enum<?>) value));
                } else if (CodeType.LongCode == codeType) {
                    out.value(CodeEnumUtils.getLongCode((Enum<?>) value));
                }
            }

            @Override
            public T read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
                
                if (CodeType.Code == codeType) {
                    return CodeEnumUtils.getEnum(getEnumMethod, Integer.parseInt(reader.nextString()));
                } else if (CodeType.StringCode == codeType) {
                    return CodeEnumUtils.getEnum(getEnumMethod, reader.nextString());
                } else if (CodeType.LongCode == codeType) {
                    return CodeEnumUtils.getEnum(getEnumMethod, Long.parseLong(reader.nextString()));
                } else {
                    return CodeEnumUtils.getEnum(getEnumMethod, Integer.parseInt(reader.nextString()));
                }
            }
        };
    }
}

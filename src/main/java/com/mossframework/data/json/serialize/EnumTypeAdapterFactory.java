package com.mossframework.data.json.serialize;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mossframework.code.CodeEnumUtils;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        @SuppressWarnings("unchecked")
        final Class<T> rawType = (Class<T>) type.getRawType();
        if (!rawType.isEnum()) {
          return null;
        }
        
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (null == value) {
                    out.nullValue();
                } else {
                    out.value(CodeEnumUtils.getCode((Enum<?>)value));
                }                
            }

            @Override
            public T read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                } else {
                    return CodeEnumUtils.getEnum(rawType, Integer.parseInt(reader.nextString()));
                }
            }
        };
    }


}

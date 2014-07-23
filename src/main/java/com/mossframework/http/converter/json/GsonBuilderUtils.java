package com.mossframework.http.converter.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 4.1이 아직 Release되지 않아서 코드를 복사해 옴. Spring 4.1업데이트 이후에 제거 필요
 * 
 * A simple utility class for obtaining a Google Gson 2.x {@link GsonBuilder}
 * which Base64-encodes {@code byte[]} properties when reading and writing JSON.
 *
 * @author Juergen Hoeller
 * @author Roy Clarkson
 * @since 4.1
 * @see GsonFactoryBean#setBase64EncodeByteArrays
 * @see com.mossframework.http.converter.json.GsonBase64Utils
 */
public abstract class GsonBuilderUtils {

    /**
     * Obtain a {@link GsonBuilder} which Base64-encodes {@code byte[]}
     * properties when reading and writing JSON.
     * <p>A custom {@link com.google.gson.TypeAdapter} will be registered via
     * {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)} which
     * serializes a {@code byte[]} property to and from a Base64-encoded String
     * instead of a JSON array.
     * <p><strong>NOTE:</strong> Use of this option requires the presence of the
     * Apache Commons Codec library on the classpath when running on Java 6 or 7.
     * On Java 8, the standard {@link java.util.Base64} facility is used instead.
     */
    public static GsonBuilder gsonBuilderWithBase64EncodedByteArrays() {
        // Assert that Base64 support is available, as long we're not on Java 8+
        GsonBase64Utils.encode(null);

        // Now, construct a pre-configured GsonBuilder...
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(byte[].class, new Base64TypeAdapter());
        return builder;
    }


    private static class Base64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

        @Override
        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(GsonBase64Utils.encodeToString(src));
        }

        @Override
        public byte[] deserialize(JsonElement json, Type type, JsonDeserializationContext cxt) {
            return GsonBase64Utils.decodeFromString(json.getAsString());
        }
    }

}
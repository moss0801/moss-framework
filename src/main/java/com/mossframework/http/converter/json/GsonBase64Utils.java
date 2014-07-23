package com.mossframework.http.converter.json;

import java.nio.charset.Charset;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * GsonHttpMessageConverter를 사용하기 위해서 추가.
 * 실제 package: org.springframework.util.Base64Utils 
 * Java7에서 빌드하기 위해서 Java8 관련 코드 제거
 *  
 * A simple utility class for Base64 encoding and decoding.
 *
 * <p>Adapts to either Java 8's {@link java.util.Base64} class or Apache
 * Commons Codec's {@link org.apache.commons.codec.binary.Base64} class.
 * With neither Java 8 nor Commons Codec present, encode/decode calls
 * will fail with an IllegalStateException.
 *
 * @author Juergen Hoeller
 * @since 4.1
 * @see java.util.Base64
 * @see org.apache.commons.codec.binary.Base64
 */
public abstract class GsonBase64Utils {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    private static final Base64Delegate delegate;

    static {
        Base64Delegate delegateToUse = null;
        // Apache Commons Codec present on the classpath?
        if (ClassUtils.isPresent("org.apache.commons.codec.binary.Base64", GsonBase64Utils.class.getClassLoader())) {
            delegateToUse = new CommonsCodecBase64Delegate();
        }
        delegate = delegateToUse;
    }

    /**
     * Assert that Byte64 encoding is actually supported.
     * @throws IllegalStateException if neither Java 8 nor Apache Commons Codec is present
     */
    private static void assertSupported() {
        Assert.state(delegate != null, "Neither Java 8 nor Apache Commons Codec found - Base64 encoding not supported");
    }


    /**
     * Base64-encode the given byte array.
     * @param src the original byte array (may be {@code null})
     * @return the encoded byte array (or {@code null} if the input was {@code null})
     * @throws IllegalStateException if Base64 encoding is not supported,
     * i.e. neither Java 8 nor Apache Commons Codec is present at runtime
     */
    public static byte[] encode(byte[] src) {
        assertSupported();
        return delegate.encode(src);
    }

    /**
     * Base64-encode the given byte array to a String.
     * @param src the original byte array (may be {@code null})
     * @return the encoded byte array as a UTF-8 String
     * (or {@code null} if the input was {@code null})
     * @throws IllegalStateException if Base64 encoding is not supported,
     * i.e. neither Java 8 nor Apache Commons Codec is present at runtime
     */
    public static String encodeToString(byte[] src) {
        assertSupported();
        if (src == null) {
            return null;
        }
        if (src.length == 0) {
            return "";
        }
        return new String(delegate.encode(src), DEFAULT_CHARSET);
    }

    /**
     * Base64-decode the given byte array.
     * @param src the encoded byte array (may be {@code null})
     * @return the original byte array (or {@code null} if the input was {@code null})
     * @throws IllegalStateException if Base64 encoding is not supported,
     * i.e. neither Java 8 nor Apache Commons Codec is present at runtime
     */
    public static byte[] decode(byte[] src) {
        assertSupported();
        return delegate.decode(src);
    }

    /**
     * Base64-decode the given byte array from an UTF-8 String.
     * @param src the encoded UTF-8 String (may be {@code null})
     * @return the original byte array (or {@code null} if the input was {@code null})
     * @throws IllegalStateException if Base64 encoding is not supported,
     * i.e. neither Java 8 nor Apache Commons Codec is present at runtime
     */
    public static byte[] decodeFromString(String src) {
        assertSupported();
        if (src == null) {
            return null;
        }
        if (src.length() == 0) {
            return new byte[0];
        }
        return delegate.decode(src.getBytes(DEFAULT_CHARSET));
    }


    private interface Base64Delegate {

        byte[] encode(byte[] src);

        byte[] decode(byte[] src);
    }


    private static class CommonsCodecBase64Delegate implements Base64Delegate {

        private final org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();

        public byte[] encode(byte[] src) {
            return this.base64.encode(src);
        }

        public byte[] decode(byte[] src) {
            return this.base64.decode(src);
        }
    }

}

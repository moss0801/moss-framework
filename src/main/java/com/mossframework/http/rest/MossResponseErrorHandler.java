package com.mossframework.http.rest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

public class MossResponseErrorHandler extends DefaultResponseErrorHandler {
    
    Logger log = LoggerFactory.getLogger(MossResponseErrorHandler.class);
    
    /**
     * ResponseBody deserialize를 위한 MessageConverter
     */
    @SuppressWarnings("rawtypes")
    private HttpMessageConverter httpMessageConverter;

    @SuppressWarnings("rawtypes")
    public void setHttpMessageConverter(HttpMessageConverter httpMessageConverter) {
        this.httpMessageConverter = httpMessageConverter;
    }

    /**
     * 응답 Status가 200이 아닌경우 모두 Error로 간주한다.
     */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return HttpStatus.OK != getHttpStatusCode(response);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = getHttpStatusCode(response);
        ErrorResult result = null;
        try{
            result = (ErrorResult) httpMessageConverter.read(ErrorResult.class, response);
        } catch (Exception ex) {
            // ignore
        }
        
        if (null != result) {
            // 에러 포맷으로 반환된 경우
            // ErrorCode 조회
            ErrorCode errorCode = ErrorCode.getEnum(result.getCode());
            log.error("fail to get response from server, httpStatus={}({}), errorCode={}, result={}", statusCode.value(), statusCode.getReasonPhrase(), errorCode, result);
            // throw exception instead of logging
            throw new RuntimeException("something wrong");
        } else {
            throw new HttpClientErrorException(statusCode, response.getStatusText(),
                    response.getHeaders(), getResponseBody(response), getCharset(response));
        }
    }
    
    private HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode;
        try {
            statusCode = response.getStatusCode();
        }
        catch (IllegalArgumentException ex) {
            throw new UnknownHttpStatusCodeException(response.getRawStatusCode(),
                    response.getStatusText(), response.getHeaders(), getResponseBody(response), getCharset(response));
        }
        return statusCode;
    }
    
    private byte[] getResponseBody(ClientHttpResponse response) {
        try {
            InputStream responseBody = response.getBody();
            if (responseBody != null) {
                return FileCopyUtils.copyToByteArray(responseBody);
            }
        }
        catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }
    
    private Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharSet() : null;
    }

}

package com.njupt.system.util;


import org.springframework.core.MethodParameter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Elaine Huang
 * @date 2021/12/20
 */
@ControllerAdvice
@Component
public class CustomJsonHttpContentConvert extends MappingJackson2HttpMessageConverter implements ResponseBodyAdvice {
    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        if (!(object instanceof HttpResponse)){
            object = HttpResponse.success(object);
        }
        super.writeInternal(object,type,httpOutputMessage);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class convertType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType contentType, Class convertType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null){
            return HttpResponse.success(null);
        }else {
            return body;
        }
    }
}

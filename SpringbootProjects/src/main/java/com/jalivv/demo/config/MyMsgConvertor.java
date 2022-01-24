package com.jalivv.demo.config;

import com.jalivv.demo.entity.PersonEntity;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/24 08:22
 */
public class MyMsgConvertor<T> implements HttpMessageConverter<T> {
    MediaType jalivvMediaType = MediaType.parseMediaType("application/x-jalivv");

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(PersonEntity.class) && (jalivvMediaType.equals(mediaType) || mediaType == null);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(jalivvMediaType);
    }

    @Override
    public T read(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(T t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String outStr = "jalivv [" + t + " ]";
        outputMessage.getBody().write(outStr.getBytes(StandardCharsets.UTF_8));
    }
}

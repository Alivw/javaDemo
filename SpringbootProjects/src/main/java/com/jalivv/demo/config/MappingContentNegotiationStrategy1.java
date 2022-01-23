package com.jalivv.demo.config;

import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.AbstractMappingContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/23 21:15
 */
public class MappingContentNegotiationStrategy1 extends AbstractMappingContentNegotiationStrategy {


    public MappingContentNegotiationStrategy1(Map<String, MediaType> mediaTypes) {
        super(mediaTypes);
    }

    @Override
    public List<MediaType> resolveMediaTypes(NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException {

        return super.resolveMediaTypes(webRequest);
    }

    @Override
    protected String getMediaTypeKey(NativeWebRequest request) {
        return request.getParameter("jalivv");
    }

    @Override
    protected MediaType handleNoMatch(NativeWebRequest request, String key) throws HttpMediaTypeNotAcceptableException {
        return super.handleNoMatch(request, key);
    }
}

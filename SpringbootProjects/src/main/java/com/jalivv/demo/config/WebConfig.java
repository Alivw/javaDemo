package com.jalivv.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description WebConfig
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/23 08:45
 */
@Configuration
public class WebConfig {

    public MSGConvertor msgConvertor() {
        return new MSGConvertor();
    }

    @Bean
    public MappingContentNegotiationStrategy1 mappingContentNegotiationStrategy1() {
        return new MappingContentNegotiationStrategy1(null);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Date>() {
                    @Override
                    public Date convert(String source) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date parse = null;
                        try {
                            parse = format.parse(source);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return parse;
                    }
                });
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                //converters.add(msgConvertor());
            }

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                List<ContentNegotiationStrategy> strategies = new ArrayList<>();
                strategies.add(mappingContentNegotiationStrategy1());
                configurer.strategies(strategies);
            }
        };
    }

}

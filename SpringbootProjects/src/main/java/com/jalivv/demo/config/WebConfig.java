package com.jalivv.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
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

    @Bean
    public MyMsgConvertor msgConvertor() {
        return new MyMsgConvertor();
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
                // 放入容器，这里不用加
                //converters.add(msgConvertor());
            }

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                List<ContentNegotiationStrategy> strategies = new ArrayList<>();
                Map<String, MediaType> mediaTypes = new HashMap<>();
                mediaTypes.put("jalison", MediaType.parseMediaType("application/x-jalivv"));
                ParameterContentNegotiationStrategy negotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
                negotiationStrategy.setParameterName("mediaType");
                strategies.add(negotiationStrategy);
                configurer.strategies(strategies);
            }
        };
    }

}

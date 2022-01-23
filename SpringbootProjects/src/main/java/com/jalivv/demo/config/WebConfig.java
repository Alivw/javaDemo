package com.jalivv.demo.config;

import com.jalivv.demo.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description WebConfig
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/23 08:45
 */
@Configuration
public class WebConfig {

    @Bean
    public MSGConvertor msgConvertor() {
        return new MSGConvertor();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

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
                converters.add(msgConvertor());
            }

        };

        return webMvcConfigurer;
    }

}

package com.jalivv.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * @Description WebConfig
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/23 08:45
 */
@Configuration
public class WebConfig {


    @Bean
    @ConditionalOnBean(name = "requestMappingHandlerAdapter")
    public WebMvcConfigurer webMvcConfigurer() {

        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {

                registry.addConverter((source) -> {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse = null;
                    try {
                        parse = format.parse(source.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return parse;
                });


            }


            //@Override
            //@ConditionalOnBean(RequestMappingHandlerAdapter.class)
            //public void addFormatters(FormatterRegistry registry) {
            //    registry.addConverter(s-> {
            //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //        Date date = null;
            //        try {
            //            date = format.parse(s.toString());
            //        } catch (ParseException e) {
            //            e.printStackTrace();
            //        }
            //        return date;
            //    });
            //}
        };

        return webMvcConfigurer;
    }

}

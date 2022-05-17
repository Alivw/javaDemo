package com.jalivv.maven.jb;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Now {

    private String code;
    private String visibility;
    private String wind_direction;
    private String pressure;
    private String clouds;
    private String feels_like;
    private String dew_point;
    private String wind_scale;
    private String temperature;
    private String humidity;
    private String wind_direction_degree;
    private String wind_speed;
    private String text;

}

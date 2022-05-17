package com.jalivv.maven.jb;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class XinZhiWeather implements Serializable {

    private String Fid;
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
    private Date last_update;

//    private Now now;
//
//    private Date last_update;

}

package com.jalivv.maven.jb;

/**
 * @Description
 * @Date 2022/5/17 18:56
 * @Created by jalivv
 */
public class Main {
    public static void main(String[] args) {
        Now now = new Now();
        now.setClouds("clouds").setCode("code").setDew_point("dew_point")
                .setHumidity("humidity").setFeels_like("feels_like")
                .setTemperature("temperature").setPressure("pressure")
                .setText("text").setVisibility("visibility").setWind_direction("wind_direction")
                .setWind_direction_degree("wind_derection_degree").setWind_scale("wind_scale")
                .setWind_speed("wind_speed");

        Results r = new Results();

    }
}

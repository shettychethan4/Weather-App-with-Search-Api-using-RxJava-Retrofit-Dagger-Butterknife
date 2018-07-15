package com.chethan.myapplication.domain.models.weathermodel;

import com.chethan.myapplication.domain.models.Coord;
import com.chethan.myapplication.domain.models.Weather;

import java.util.ArrayList;
import java.util.List;




public class WeatherData {
    private Coord coord;
    private List<Weather> weather = new ArrayList<>();
    private String base;
    private Main main = new Main();
    private Wind wind = new Wind();
    private Clouds clouds = new Clouds();
    private Integer dt;
    private Sys sys = new Sys();
    private Integer id;
    private String name;
    private Integer cod;

    public WeatherData(){
    }

    public String getBase() {
        return base;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Integer getCod() {
        return cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public Integer getDt() {
        return dt;
    }

    public Integer getId() {
        return id;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Sys getSys() {
        return sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }

}

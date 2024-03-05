package com.project.weatherapp.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String city;

    private Date timestamp;
    @JsonProperty("current_condition")
    @OneToMany(mappedBy = "weather", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrentCondition> currentCondition;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CurrentCondition> getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(List<CurrentCondition> currentCondition) {
        this.currentCondition = currentCondition;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void updateCurrentConditions() {
        for (CurrentCondition condition : this.currentCondition) {
            condition.setWeather(this);
            condition.updateWeatherDescs();
        }
    }
}

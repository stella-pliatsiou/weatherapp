package com.project.weatherapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WeatherDesc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    // Value field with JSON property mapping
    @JsonProperty("value")
    @Column(name = "`value`") // For MySQL
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_condition_id", nullable = false)
    @JsonIgnore
    private CurrentCondition currentCondition;

  // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

      // Getter for value
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }
}

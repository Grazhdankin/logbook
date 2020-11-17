package com.gt.logbook.domain.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "weather_logs")
public class WeatherLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5372740146212121289L;

    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_log_id", nullable = false)
    private GeneralLog generalLog;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private LocalTime time;

    @Column(name = "wind_direction_and_velocity")
    private String windDirectionAndVelocity;

    @Column
    private String sea;

    @Column(name = "weather_and_visibility")
    private String weatherAndVisibility;

    @Column(name = "air_pressure")
    private Short airPressure;

    @Column(name = "air_temperature")
    private Float airTemperature;

    @Column(name = "water_temperature")
    private Float waterTemperature;

    @Column(name = "cargo_holds")
    private Short cargoHolds;

    @Column(name = "bildge_water_level")
    private Short bildgeWaterLevel;
}

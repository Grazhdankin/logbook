package com.gt.logbook.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Audited
@Table(name = "general_logs")
public class GeneralLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2095175688627568153L;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "lights_on_time")
    private LocalTime lightsOnTime;

    @Column(name = "lights_off_time")
    private LocalTime lightsOffTime;

    @Column(name = "diesel_fuel_consumption")
    private Float dieselFuelConsumption;

    @Column(name = "diesel_fuel_retention")
    private Float dieselFuelRetention;

    @Column(name = "heavy_fuel_consumption")
    private Float heavyFuelConsumption;

    @Column(name = "heavy_fuel_retention")
    private Float heavyFuelRetention;

    @Column(name = "fresh_water_consumption")
    private Float freshWaterConsumption;

    @Column(name = "fresh_water_retention")
    private Float freshWaterRetention;

    @Column
    private String note;

    @NotAudited
    @OneToMany(mappedBy = "generalLog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<WeatherLog> weatherLogs;

    @NotAudited
    @OneToMany(mappedBy = "generalLog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PassageLog> passageLogs;

    @NotAudited
    @OneToMany(mappedBy = "generalLog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TanksLog> tanksLogs;

    @NotAudited
    @OneToMany(mappedBy = "generalLog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CommonLog> commonLogs;
}
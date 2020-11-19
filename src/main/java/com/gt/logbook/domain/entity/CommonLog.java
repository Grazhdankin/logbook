package com.gt.logbook.domain.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
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
@Table(name = "common_logs")
public class CommonLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2750010849832234628L;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "general_log_id", nullable = false)
    private GeneralLog generalLog;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private LocalTime time;

    @Column
    private String location;

    @Column(nullable = false)
    private String log;
}
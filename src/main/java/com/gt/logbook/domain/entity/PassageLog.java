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
@Table(name = "passage_logs")
public class PassageLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -198513277173007649L;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "general_log_id", nullable = false)
    private GeneralLog generalLog;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private Float passage;

    @ManyToOne
    @JoinColumn(name = "officer_of_the_watch_id", nullable = false)
    private User officerOfTheWatch;

    @ManyToOne
    @JoinColumn(name = "seamen_of_the_watch_id", nullable = false)
    private User seamenOfTheWatch;
}
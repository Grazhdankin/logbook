package com.gt.logbook.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tanks")
public class Tank extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -841345638860558515L;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
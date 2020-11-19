package com.gt.logbook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.Tank;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long>, RevisionRepository<Tank, Long, Integer> {
}
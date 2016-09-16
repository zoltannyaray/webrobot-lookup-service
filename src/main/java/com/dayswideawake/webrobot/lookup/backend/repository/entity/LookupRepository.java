package com.dayswideawake.webrobot.lookup.backend.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupRepository extends JpaRepository<LookupEntity, Long> {

}

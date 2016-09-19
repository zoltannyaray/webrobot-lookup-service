package com.dayswideawake.webrobot.lookup.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dayswideawake.webrobot.lookup.backend.repository.entity.LookupEntity;

@Repository
public interface LookupRepository extends JpaRepository<LookupEntity, Long> {

}

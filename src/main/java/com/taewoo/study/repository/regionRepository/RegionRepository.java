package com.taewoo.study.repository.regionRepository;

import com.taewoo.study.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}

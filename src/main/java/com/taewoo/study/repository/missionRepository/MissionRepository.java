package com.taewoo.study.repository.missionRepository;

import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}

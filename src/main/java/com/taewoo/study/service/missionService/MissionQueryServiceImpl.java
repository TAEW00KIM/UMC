package com.taewoo.study.service.missionService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.StoreHandler;
import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.Store;
import com.taewoo.study.repository.missionRepository.MissionRepository;
import com.taewoo.study.repository.storeRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<Mission> findAvailableMissions(String regionName, Long memberId, Long cursorId, int limit) {
        return missionRepository.findAvailableMissions(regionName, memberId, cursorId, limit);
    }

    @Override
    public Page<Mission> getStoreMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "deadline"));
        return missionRepository.findAllByStore(store, pageable);
    }
}

package com.taewoo.study.service.missionService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.StoreHandler;
import com.taewoo.study.converter.MissionConverter;
import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.Store;
import com.taewoo.study.repository.missionRepository.MissionRepository;
import com.taewoo.study.repository.storeRepository.StoreRepository;
import com.taewoo.study.web.dto.missionDto.MissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.CreateDTO request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMission(request, store);
        return missionRepository.save(mission);

    }
}

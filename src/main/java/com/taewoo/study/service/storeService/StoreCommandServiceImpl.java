package com.taewoo.study.service.storeService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.RegionHandler;
import com.taewoo.study.converter.StoreConverter;
import com.taewoo.study.domain.Region;
import com.taewoo.study.domain.Store;
import com.taewoo.study.repository.regionRepository.RegionRepository;
import com.taewoo.study.repository.storeRepository.StoreRepository;
import com.taewoo.study.web.dto.storeDto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateDTO request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        Store store = StoreConverter.toStore(request, region);
        return storeRepository.save(store);
    }
}

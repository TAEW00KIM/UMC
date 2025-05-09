package com.taewoo.study.converter;

import com.taewoo.study.domain.Region;
import com.taewoo.study.domain.Store;
import com.taewoo.study.web.dto.storeDto.StoreRequestDTO;
import com.taewoo.study.web.dto.storeDto.StoreResponseDTO;

public class StoreConverter {
    public static StoreResponseDTO.CreateResultDTO toCreateResultDTO(Store store) {
        return StoreResponseDTO.CreateResultDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
    
    public static Store toStore(StoreRequestDTO.CreateDTO request, Region region) {
        return Store.builder()
                .region(region)
                .name(request.getName())
                .address(request.getAddress())
                .score(0f)
                .build();
    }
}

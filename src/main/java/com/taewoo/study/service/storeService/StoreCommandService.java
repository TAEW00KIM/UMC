package com.taewoo.study.service.storeService;

import com.taewoo.study.domain.Store;
import com.taewoo.study.web.dto.storeDto.StoreRequestDTO;
import jakarta.validation.Valid;

public interface StoreCommandService {
    Store createStore(@Valid StoreRequestDTO.CreateDTO storeRequestDTO);
}

package com.taewoo.study.repository.storeRepository;

import com.taewoo.study.domain.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}

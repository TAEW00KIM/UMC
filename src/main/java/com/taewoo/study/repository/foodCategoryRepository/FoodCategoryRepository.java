package com.taewoo.study.repository.foodCategoryRepository;

import com.taewoo.study.domain.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}

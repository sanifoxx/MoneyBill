package com.moneybill.moneybill.util.mapper;

import com.moneybill.moneybill.dto.category.CategoryInfoDto;
import com.moneybill.moneybill.model.Category;

public final class CategoryMapper {

    public static CategoryInfoDto toInfoDto(Category category) {
        return CategoryInfoDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

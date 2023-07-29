package com.moneybill.moneybill.service.category;

import com.moneybill.moneybill.dto.category.CategoryCreateDto;
import com.moneybill.moneybill.dto.category.CategoryInfoDto;

import java.util.List;

public interface CategoryService {

    CategoryInfoDto createCategory(CategoryCreateDto categoryCreateDto);

    List<CategoryInfoDto> getAllCategories();
}

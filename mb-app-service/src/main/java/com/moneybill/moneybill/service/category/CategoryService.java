package com.moneybill.moneybill.service.category;

import com.moneybill.moneybill.dto.category.CategoryCreateDto;
import com.moneybill.moneybill.dto.category.CategoryInfoDto;
import com.moneybill.moneybill.dto.category.CategoryUpdateDto;
import com.moneybill.moneybill.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryInfoDto createCategory(CategoryCreateDto categoryCreateDto);

    List<CategoryInfoDto> getAllCategories();

    CategoryInfoDto getCategoryById(Long categoryId);

    Category getCategoryByIdOrElseThrow(Long categoryId);

    CategoryInfoDto updateCategoryById(Long categoryId, CategoryUpdateDto categoryUpdateDto);

    CategoryInfoDto deleteCategoryById(Long categoryId);
}
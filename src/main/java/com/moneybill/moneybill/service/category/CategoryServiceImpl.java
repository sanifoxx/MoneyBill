package com.moneybill.moneybill.service.category;

import com.moneybill.moneybill.dto.category.CategoryCreateDto;
import com.moneybill.moneybill.dto.category.CategoryInfoDto;
import com.moneybill.moneybill.dto.category.CategoryUpdateDto;
import com.moneybill.moneybill.exception.already_exists.AlreadyExistsException;
import com.moneybill.moneybill.exception.already_exists.CategoryAlreadyExistsException;
import com.moneybill.moneybill.exception.not_found.CategoryNotFoundException;
import com.moneybill.moneybill.model.Category;
import com.moneybill.moneybill.repository.category.CategoryRepository;
import com.moneybill.moneybill.util.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryInfoDto createCategory(CategoryCreateDto categoryCreateDto) {
        if (categoryRepository.existsByName(categoryCreateDto.getName())) {
            throw new CategoryAlreadyExistsException("The category already exists");
        }
        final Category category = Category.builder()
                .name(categoryCreateDto.getName())
                .build();
        return CategoryMapper.toInfoDto(categoryRepository.save(category));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryInfoDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toInfoDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryInfoDto getCategoryById(Long categoryId) {
        Category category = getCategoryByIdOrElseThrow(categoryId);
        return CategoryMapper.toInfoDto(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryByIdOrElseThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found")
                );
    }

    @Transactional
    @Override
    public CategoryInfoDto updateCategoryById(Long categoryId, CategoryUpdateDto categoryUpdateDto) {
        Category category = getCategoryByIdOrElseThrow(categoryId);
        if (categoryUpdateDto.getName() != null) {
            if (categoryRepository.existsByNameAndIdNot(categoryUpdateDto.getName(), categoryId)) {
                throw new AlreadyExistsException("The name of category already exists");
            }
            category.setName(categoryUpdateDto.getName());
        }
        return CategoryMapper.toInfoDto(categoryRepository.save(category));
    }

    @Transactional
    @Override
    public CategoryInfoDto deleteCategoryById(Long categoryId) {
        Category category = getCategoryByIdOrElseThrow(categoryId);
        categoryRepository.delete(category);
        return CategoryMapper.toInfoDto(category);
    }
}

package com.moneybill.moneybill.service.category;

import com.moneybill.moneybill.dto.category.CategoryCreateDto;
import com.moneybill.moneybill.dto.category.CategoryInfoDto;
import com.moneybill.moneybill.exception.already_exists.CategoryAlreadyExistsException;
import com.moneybill.moneybill.model.Category;
import com.moneybill.moneybill.repository.category.CategoryRepository;
import com.moneybill.moneybill.util.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

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
}

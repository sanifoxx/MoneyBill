package com.moneybill.moneybill.controller.category;

import com.moneybill.moneybill.dto.category.CategoryCreateDto;
import com.moneybill.moneybill.dto.category.CategoryInfoDto;
import com.moneybill.moneybill.service.category.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryInfoDto createCategory(@Valid @RequestBody CategoryCreateDto categoryCreateDto) {
        log.info("POST /categories | categoryCreateDto-Object: {}", categoryCreateDto);
        return categoryService.createCategory(categoryCreateDto);
    }

    @GetMapping
    public List<CategoryInfoDto> getAllCategories() {
        log.info("GET /categories");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryInfoDto getCategory(@PathVariable(name = "categoryId") Long categoryId) {
        log.info("GET /categories/{}", categoryId);
        return categoryService.getCategoryById(categoryId);
    }
}

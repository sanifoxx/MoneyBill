package com.moneybill.moneybill.repository.category;

import com.moneybill.moneybill.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long categoryId);
}

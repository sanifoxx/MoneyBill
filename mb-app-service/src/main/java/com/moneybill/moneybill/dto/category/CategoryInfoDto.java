package com.moneybill.moneybill.dto.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryInfoDto {

    private Long id;
    private String name;
}

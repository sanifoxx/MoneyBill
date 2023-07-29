package com.moneybill.moneybill.dto.category;

import com.moneybill.moneybill.annotation.null_or_not_blank.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CategoryUpdateDto {

    @NullOrNotBlank
    @Size(max = 64)
    private String name;
}

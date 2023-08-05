package com.moneybill.moneybill.dto.transfer;

import com.moneybill.moneybill.annotation.null_or_not_blank.NullOrNotBlank;
import com.moneybill.moneybill.dto.category.CategoryInfoDto;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class TransferCreateDto {

    @NotNull
    @Positive
    @DecimalMax("999999999999999.99")
    private BigDecimal amount;

    @NotNull
    private Boolean isIncome;

    @NullOrNotBlank
    @Size(max = 128)
    private String description;

    @NotNull
    private CategoryInfoDto category;
}

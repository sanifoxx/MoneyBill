package com.moneybill.moneybill.dto.transfer;

import com.moneybill.moneybill.dto.category.CategoryInfoDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransferInfoDto {

    private Long id;
    private BigDecimal amount;
    private Boolean isIncome;
    private String description;
    private CategoryInfoDto category;
    private LocalDateTime creationTimestamp;
}

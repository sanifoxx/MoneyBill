package com.moneybill.moneybill.util.mapper;

import com.moneybill.moneybill.dto.transfer.TransferInfoDto;
import com.moneybill.moneybill.model.Transfer;

public final class TransferMapper {

    public static TransferInfoDto toInfoDto(Transfer transfer) {
        return TransferInfoDto.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .isIncome(transfer.getIsIncome())
                .description(transfer.getDescription())
                .category(CategoryMapper.toInfoDto(transfer.getCategory()))
                .creationTimestamp(transfer.getCreationTimestamp())
                .build();
    }
}

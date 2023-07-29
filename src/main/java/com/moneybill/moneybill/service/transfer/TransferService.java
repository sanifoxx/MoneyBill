package com.moneybill.moneybill.service.transfer;

import com.moneybill.moneybill.dto.transfer.TransferCreateDto;
import com.moneybill.moneybill.dto.transfer.TransferInfoDto;

import java.util.List;

public interface TransferService {

    TransferInfoDto createTransferForUser(Long userId, TransferCreateDto transferCreateDto);

    List<TransferInfoDto> getAllTransfersForUser(Long userId);
}

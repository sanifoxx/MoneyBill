package com.moneybill.moneybill.service.transfer;

import com.moneybill.moneybill.dto.transfer.TransferCreateDto;
import com.moneybill.moneybill.dto.transfer.TransferInfoDto;
import com.moneybill.moneybill.model.Transfer;

import java.util.List;

public interface TransferService {

    TransferInfoDto createTransferForUser(Long userId, TransferCreateDto transferCreateDto);

    List<TransferInfoDto> getAllTransfersForUser(Long userId);

    TransferInfoDto getTransferByIdForUser(Long userId, Long transferId);

    Transfer getTransferByIdOrElseThrow(Long transferId);
}

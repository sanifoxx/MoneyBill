package com.moneybill.moneybill.controller.api_v1.transfer;

import com.moneybill.moneybill.dto.transfer.TransferCreateDto;
import com.moneybill.moneybill.dto.transfer.TransferInfoDto;
import com.moneybill.moneybill.dto.transfer.TransferUpdateDto;
import com.moneybill.moneybill.service.transfer.TransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/transfers")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public TransferInfoDto createTransfer(@RequestHeader("X-User-Id") Long userId,
                                          @Valid @RequestBody TransferCreateDto transferCreateDto) {
        log.info("POST /transfers | X-User-Id={}, transferCreateDto-Object: {}", userId, transferCreateDto);
        return transferService.createTransferForUser(userId, transferCreateDto);
    }

    @GetMapping
    public List<TransferInfoDto> getAllTransfers(@RequestHeader("X-User-Id") Long userId) {
        log.info("GET /transfers | X-User-Id={}", userId);
        return transferService.getAllTransfersForUser(userId);
    }

    @GetMapping("/{transferId}")
    public TransferInfoDto getTransfer(@RequestHeader("X-User-Id") Long userId,
                                       @PathVariable("transferId") Long transferId) {
        log.info("GET /transfers/{} | X-User-Id={}", transferId, userId);
        return transferService.getTransferByIdForUser(userId, transferId);
    }

    @PutMapping("/{transferId}")
    public TransferInfoDto updateTransfer(@RequestHeader("X-User-Id") Long userId,
                                          @PathVariable("transferId") Long transferId,
                                          @Valid @RequestBody TransferUpdateDto transferUpdateDto) {
        log.info("PUT /transfers/{} | X-User-Id={}, transferUpdateDto-Object: {}",
                transferId,
                userId,
                transferUpdateDto
        );
        return transferService.updateTransferByIdForUser(userId, transferId, transferUpdateDto);
    }

    @DeleteMapping("/{transferId}")
    public TransferInfoDto deleteTransfer(@RequestHeader("X-User-Id") Long userId,
                                          @PathVariable("transferId") Long transferId) {
        log.info("DELETE /transfers/{}, | X-User-Id={}", transferId, userId);
        return transferService.deleteTransferByIdForUser(userId, transferId);
    }
}

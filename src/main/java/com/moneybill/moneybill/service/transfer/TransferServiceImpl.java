package com.moneybill.moneybill.service.transfer;

import com.moneybill.moneybill.dto.transfer.TransferCreateDto;
import com.moneybill.moneybill.dto.transfer.TransferInfoDto;
import com.moneybill.moneybill.model.Category;
import com.moneybill.moneybill.model.Transfer;
import com.moneybill.moneybill.model.User;
import com.moneybill.moneybill.repository.transfer.TransferRepository;
import com.moneybill.moneybill.service.category.CategoryService;
import com.moneybill.moneybill.service.user.UserService;
import com.moneybill.moneybill.util.mapper.TransferMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    private final UserService userService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public TransferInfoDto createTransferForUser(Long userId, TransferCreateDto transferCreateDto) {
        final User owner = userService.getUserByIdOrElseThrow(userId);
        final Category category = categoryService.getCategoryByIdOrElseThrow(transferCreateDto.getCategory().getId());
        final BigDecimal amount = transferCreateDto.getAmount().setScale(2, RoundingMode.DOWN);
        final Transfer transfer = Transfer.builder()
                .owner(owner)
                .amount(amount)
                .isIncome(transferCreateDto.getIsIncome())
                .description(transferCreateDto.getDescription())
                .category(category)
                .creationTimestamp(LocalDateTime.now())
                .build();
        return TransferMapper.toInfoDto(transferRepository.save(transfer));
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransferInfoDto> getAllTransfersForUser(Long userId) {
        return transferRepository.getAllByOwnerId(userId)
                .stream()
                .map(TransferMapper::toInfoDto)
                .collect(Collectors.toList());
    }
}

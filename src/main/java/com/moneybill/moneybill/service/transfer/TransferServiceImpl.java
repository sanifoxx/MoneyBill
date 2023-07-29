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
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    private final UserService userService;
    private final CategoryService categoryService;

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
}

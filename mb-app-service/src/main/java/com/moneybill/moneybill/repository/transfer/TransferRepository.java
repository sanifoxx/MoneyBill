package com.moneybill.moneybill.repository.transfer;

import com.moneybill.moneybill.model.Transfer;
import com.moneybill.moneybill.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> getAllByOwnerId(Long userId);
}

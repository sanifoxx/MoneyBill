package com.moneybill.mbstatisticsservice.repository;

import com.moneybill.mbstatisticsservice.model.RequestEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RequestEventRepository extends JpaRepository<RequestEvent, Long> {

    @Query(value = "\n" +
            " SELECT re " +
            "   FROM RequestEvent re " +
            "  WHERE re.ipAddress LIKE :ipLike " +
            "        AND re.requestUri LIKE :uriLike "
    )
    List<RequestEvent> getRequestEventsByParams(String ipLike, String uriLike, Pageable page);
}

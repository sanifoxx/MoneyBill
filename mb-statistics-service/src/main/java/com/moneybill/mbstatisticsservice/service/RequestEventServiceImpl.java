package com.moneybill.mbstatisticsservice.service;

import com.moneybill.commondto.RequestEventCreateDto;
import com.moneybill.mbstatisticsservice.dto.RequestEventInfoDto;
import com.moneybill.mbstatisticsservice.model.RequestEvent;
import com.moneybill.mbstatisticsservice.repository.RequestEventRepository;
import com.moneybill.mbstatisticsservice.util.mapper.RequestEventMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RequestEventServiceImpl implements RequestEventService {

    private final RequestEventRepository requestEventRepository;

    @Override
    public void createRequestEvent(RequestEventCreateDto requestEventCreateDto) {
        requestEventRepository.save(
                RequestEvent.builder()
                        .ipAddress(requestEventCreateDto.getIpAddress())
                        .requestUri(requestEventCreateDto.getRequestUri())
                        .createdAt(requestEventCreateDto.getCreatedAt())
                        .build()
        );
    }

    @Override
    public List<RequestEventInfoDto> getRequestEventsByParams(Map<String, String> params) {
        Sort sort = Sort.by(
                Sort.Direction.valueOf(params.getOrDefault("sortDirection", "ASC")),
                params.getOrDefault("sortBy", "createdAt")
        );

        Pageable page = PageRequest.of(
                Integer.parseInt(params.getOrDefault("page", "1")) - 1,
                Integer.parseInt(params.getOrDefault("count", "10")),
                sort
        );

        String ipLike = "%"
                + URLDecoder.decode(params.getOrDefault("ipLike", ""), StandardCharsets.UTF_8)
                + "%";
        String uriLike = "%"
                + URLDecoder.decode(params.getOrDefault("uriLike", ""), StandardCharsets.UTF_8)
                + "%";

        System.out.println(ipLike);
        System.out.println(uriLike);

        return requestEventRepository.getRequestEventsByParams(ipLike, uriLike, page)
                .stream()
                .map(RequestEventMapper::toInfoDto)
                .collect(Collectors.toList());
    }
}

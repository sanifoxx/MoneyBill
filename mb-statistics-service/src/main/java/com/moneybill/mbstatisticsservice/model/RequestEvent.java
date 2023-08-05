package com.moneybill.mbstatisticsservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "request_events")
public class RequestEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = IPAddressConverter.class)
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "request_uri")
    private String requestUri;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

package com.moneybill.mbstatisticsservice.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Converter
public class IPAddressConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        try {
            byte[] inetAddress = InetAddress.getByName(attribute).getAddress();
            int result = 0;
            for (byte b: inetAddress) {
                result = result << 8 | (b & 0xFF);
            }
            return result;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        return String.format("%d.%d.%d.%d",
                (dbData >> 24 & 0xFF),
                (dbData >> 16 & 0xFF),
                (dbData >> 8 & 0xFF),
                (dbData & 0xFF)
        );
    }
}

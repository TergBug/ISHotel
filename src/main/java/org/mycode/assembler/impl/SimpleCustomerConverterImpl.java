package org.mycode.assembler.impl;

import com.google.gson.Gson;
import org.mycode.assembler.SimpleCustomerConverter;
import org.mycode.dto.CustomerDto;
import org.mycode.dto.SimpleCustomerDto;
import org.mycode.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;


@Component
public class SimpleCustomerConverterImpl implements SimpleCustomerConverter {
    private Gson gson;
    private RoomService roomService;

    @Autowired
    public SimpleCustomerConverterImpl(Gson gson, RoomService roomService) {
        this.gson = gson;
        this.roomService = roomService;
    }

    @Override
    public CustomerDto convert(SimpleCustomerDto simpleDto) {
        return new CustomerDto(simpleDto.getId(), simpleDto.getFirstName(), simpleDto.getLastName(),
                simpleDto.getPassport(),
                (simpleDto.getStartDate() == null || simpleDto.getStartDate().equals("")) ? null :
                        Date.valueOf(simpleDto.getStartDate()),
                (simpleDto.getEndDate() == null || simpleDto.getEndDate().equals("")) ? null :
                        Date.valueOf(simpleDto.getEndDate()),
                BigDecimal.valueOf(simpleDto.getFees()), simpleDto.getPaymentType(),
                roomService.getById(simpleDto.getRoomId()),
                gson.fromJson(simpleDto.getServices(), ArrayList.class),
                gson.fromJson(simpleDto.getFacilities(), HashMap.class));
    }

    @Override
    public SimpleCustomerDto convert(CustomerDto dto) {
        return new SimpleCustomerDto(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getPassport(),
                dto.getStartDate().toString(), dto.getEndDate().toString(), dto.getFees().doubleValue(),
                dto.getPaymentType(), dto.getRoom().getId(), gson.toJson(dto.getServices()),
                gson.toJson(dto.getFacilities()));
    }
}

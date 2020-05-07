package org.mycode.service;

import org.mycode.dto.CustomerDto;

public interface OrderRoomService {
    long order(CustomerDto customer);

    void denyOrder(long customerId);
}

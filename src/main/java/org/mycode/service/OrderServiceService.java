package org.mycode.service;

import org.mycode.dto.ServiceDto;

public interface OrderServiceService {
    void order(String customerPassport, ServiceDto service);

    void denyOrder(String customerPassport, ServiceDto service);
}

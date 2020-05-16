package org.mycode.service.impl;

import org.mycode.dto.CustomerDto;
import org.mycode.dto.enums.CustomerState;
import org.mycode.service.CustomerService;
import org.mycode.service.OrderRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OrderRoomServiceImpl implements OrderRoomService {
    private CustomerService customerService;
    private Map<Long, CustomerDto> tempCustomers = new HashMap<>();

    @Autowired
    public OrderRoomServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public long order(CustomerDto customer) {
        if (customer.getId() <= 0) {
            customer.setId(new Random().nextInt(5000) + 1);
            tempCustomers.put(customer.getId(), customer);
            return customer.getId();
        }
        CustomerDto updatedCustomer = tempCustomers.get(customer.getId());
        if (updatedCustomer != null) {
            switch (updatedCustomer.getStateOfCustomer()) {
                case ORDERING_ROOM:
                    updatedCustomer.setRoom(customer.getRoom());
                    break;
                case ENTERING_INFO:
                    customer.setRoom(updatedCustomer.getRoom());
                    updatedCustomer = customer;
                    break;
            }
            if (updatedCustomer.getStateOfCustomer() == CustomerState.VERIFIED) {
                customerService.create(updatedCustomer);
                tempCustomers.remove(updatedCustomer.getId());
            }
            return updatedCustomer.getId();
        }
        return 0;
    }

    @Override
    public void denyOrder(long customerId) {

    }
}

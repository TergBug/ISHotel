package org.mycode.service.impl;

import org.mycode.dto.CustomerDto;
import org.mycode.service.CustomerService;
import org.mycode.service.OrderRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRoomServiceImpl implements OrderRoomService {
    private CustomerService customerService;

    @Autowired
    public OrderRoomServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public long order(CustomerDto customer) {
        if (customer.getId() <= 0) {
            return customerService.create(customer);
        }
        CustomerDto updatedCustomer = customerService.getById(customer.getId());
        switch (updatedCustomer.getStateOfCustomer()) {
            case CustomerDto.ORDERING_ROOM:
                updatedCustomer.setRoom(customer.getRoom());
                break;
            case CustomerDto.ENTERING_INFO:
                customer.setRoom(updatedCustomer.getRoom());
                updatedCustomer = customer;
                break;
        }
        customerService.update(updatedCustomer);
        return updatedCustomer.getId();
    }

    @Override
    public void denyOrder(long customerId) {

    }
}

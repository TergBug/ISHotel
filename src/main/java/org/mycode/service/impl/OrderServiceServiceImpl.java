package org.mycode.service.impl;

import org.mycode.dto.CustomerDto;
import org.mycode.dto.ServiceDto;
import org.mycode.service.CustomerService;
import org.mycode.service.OrderServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceServiceImpl implements OrderServiceService {
    private CustomerService customerService;

    @Autowired
    public OrderServiceServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void order(String customerPassport, ServiceDto service) {
        CustomerDto updatedCustomer = customerService.getByPassport(customerPassport);
        if (updatedCustomer.getServices() == null) {
            updatedCustomer.setServices(new ArrayList<>());
        }
        updatedCustomer.getServices().add(service.getName());
        customerService.update(updatedCustomer);
    }

    @Override
    public void denyOrder(String customerPassport, ServiceDto service) {
        CustomerDto updatedCustomer = customerService.getByPassport(customerPassport);
        if (updatedCustomer.getServices() != null) {
            updatedCustomer.getServices().remove(service.getName());
            customerService.update(updatedCustomer);
        }
    }
}

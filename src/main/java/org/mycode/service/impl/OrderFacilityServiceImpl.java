package org.mycode.service.impl;

import org.mycode.assembler.FacilityAssembler;
import org.mycode.dto.CustomerDto;
import org.mycode.dto.FacilityDto;
import org.mycode.model.Customer;
import org.mycode.model.embededclasses.CustomerFacilityId;
import org.mycode.repository.CustomerFacilityRepository;
import org.mycode.repository.CustomerRepository;
import org.mycode.service.CustomerService;
import org.mycode.service.OrderFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OrderFacilityServiceImpl implements OrderFacilityService {
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerFacilityRepository customerFacilityRepository;
    private FacilityAssembler facilityAssembler;

    @Autowired
    public OrderFacilityServiceImpl(CustomerService customerService, CustomerRepository customerRepository,
                                    CustomerFacilityRepository customerFacilityRepository,
                                    FacilityAssembler facilityAssembler) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.customerFacilityRepository = customerFacilityRepository;
        this.facilityAssembler = facilityAssembler;
    }

    @Override
    public void order(String customerPassport, FacilityDto facility) {
        CustomerDto updatedCustomer = customerService.getByPassport(customerPassport);
        if (updatedCustomer.getFacilities() == null) {
            updatedCustomer.setFacilities(new HashMap<>());
        }
        updatedCustomer.getFacilities().put(facility.getName(), facility.getQuantity());
        customerService.update(updatedCustomer);
    }

    @Override
    public void denyOrder(String customerPassport, FacilityDto facility) {
        Customer updatedCustomer = customerRepository.findCustomerByPassport(customerPassport);
        if (updatedCustomer.getFacilities() != null) {
            customerFacilityRepository.deleteById(
                    new CustomerFacilityId(updatedCustomer, facilityAssembler.assemble(facility)));
        }
    }
}

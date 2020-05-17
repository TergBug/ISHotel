package org.mycode.assembler.impl;

import org.mycode.assembler.CustomerAssembler;
import org.mycode.assembler.RoomAssembler;
import org.mycode.dto.CustomerDto;
import org.mycode.dto.enums.CustomerState;
import org.mycode.model.Customer;
import org.mycode.model.CustomerFacility;
import org.mycode.model.Facility;
import org.mycode.model.Service;
import org.mycode.model.embededclasses.CustomerFacilityId;
import org.mycode.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class CustomerAssemblerImpl implements CustomerAssembler {
    private PaymentTypeRepository paymentTypeRepository;
    private ServiceRepository serviceRepository;
    private FacilityRepository facilityRepository;
    private CustomerFacilityRepository customerFacilityRepository;
    private CustomerRepository customerRepository;
    private RoomAssembler roomAssembler;

    @Autowired
    public CustomerAssemblerImpl(PaymentTypeRepository paymentTypeRepository, ServiceRepository serviceRepository,
                                 FacilityRepository facilityRepository,
                                 CustomerFacilityRepository customerFacilityRepository,
                                 CustomerRepository customerRepository, RoomAssembler roomAssembler) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.serviceRepository = serviceRepository;
        this.facilityRepository = facilityRepository;
        this.customerFacilityRepository = customerFacilityRepository;
        this.customerRepository = customerRepository;
        this.roomAssembler = roomAssembler;
    }

    @Override
    public Customer assemble(CustomerDto dto) {
        Customer customer = customerRepository.findCustomerById(dto.getId());
        return new Customer(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getPassport(), dto.getStartDate(),
                dto.getEndDate(), dto.getFees(), paymentTypeRepository.findPaymentTypeByName(dto.getPaymentType()),
                roomAssembler.assemble(dto.getRoom()),
                (dto.getServices() == null ? new ArrayList<String>() : dto.getServices())
                        .stream().map(el -> serviceRepository.findServiceByName(el))
                        .collect(Collectors.toList()),
                (dto.getFacilities() == null ? new HashMap<String, Integer>() : dto.getFacilities())
                        .keySet().stream().map(el -> {
                    Facility facility = facilityRepository.findFacilityByName(el);
                    customerFacilityRepository.save(
                            new CustomerFacility(customer, facility, dto.getFacilities().get(el)));
                    return customerFacilityRepository.findCustomerFacilitiesById(
                            new CustomerFacilityId(customer, facility));
                }).collect(Collectors.toSet()));
    }

    @Override
    public CustomerDto assemble(Customer model) {
        if (model.verify() == CustomerState.ENTERING_INFO) {
            return new CustomerDto(model.getId(), roomAssembler.assemble(model.getRoom()));
        } else if (model.verify() == CustomerState.ORDERING_ROOM) {
            return new CustomerDto(model.getId(), model.getFirstName(), model.getLastName(), model.getPassport(),
                    model.getStartDate(), model.getEndDate(), model.getFees(), model.getPaymentType().getName(),
                    model.getServices().stream().map(Service::getName)
                            .collect(Collectors.toList()),
                    model.getFacilities().stream()
                            .collect(Collectors.toMap(el -> el.getId().getFacility().getName(), CustomerFacility::getQuantity)),
                    model.totalPayment());
        } else if (model.verify() == CustomerState.NOT_VERIFIED) {
            return new CustomerDto(model.getId());
        }
        return new CustomerDto(model.getId(), model.getFirstName(), model.getLastName(), model.getPassport(),
                model.getStartDate(), model.getEndDate(), model.getFees(), model.getPaymentType().getName(),
                roomAssembler.assemble(model.getRoom()),
                model.getServices().stream().map(Service::getName)
                        .collect(Collectors.toList()),
                model.getFacilities().stream()
                        .collect(Collectors.toMap(el -> el.getId().getFacility().getName(), CustomerFacility::getQuantity)),
                model.totalPayment());
    }
}

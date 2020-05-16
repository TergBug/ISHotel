package org.mycode.service.impl;

import org.apache.log4j.Logger;
import org.mycode.assembler.CustomerAssembler;
import org.mycode.dto.CustomerDto;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.repository.CustomerRepository;
import org.mycode.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = Logger.getLogger(CustomerServiceImpl.class);
    private CustomerRepository currentRepo;
    private CustomerAssembler customerAssembler;

    @Autowired
    public CustomerServiceImpl(CustomerRepository currentRepo, CustomerAssembler customerAssembler) {
        this.currentRepo = currentRepo;
        this.customerAssembler = customerAssembler;
    }

    @Override
    public long create(CustomerDto model) {
        long id;
        if ((model.getServices() != null && model.getServices().size() > 0) ||
                (model.getFacilities() != null && model.getFacilities().size() > 0)) {
            CustomerDto temp = new CustomerDto(model);
            temp.setServices(null);
            temp.setFacilities(null);
            currentRepo.save(customerAssembler.assemble(temp));
            id = currentRepo.save(customerAssembler.assemble(model)).getId();
        } else {
            id = currentRepo.save(customerAssembler.assemble(model)).getId();
        }
        log.debug("Service->Create");
        return id;
    }

    @Override
    public CustomerDto getById(long readID) {
        CustomerDto customer = customerAssembler.assemble(currentRepo.findCustomerById(readID));
        log.debug("Service->Read");
        return customer;
    }

    @Override
    public void update(CustomerDto updatedModel) {
        if (!currentRepo.findById(updatedModel.getId()).isPresent()) {
            throw new NoSuchEntryException();
        }
        currentRepo.save(customerAssembler.assemble(updatedModel));
        log.debug("Service->Update");
    }

    @Override
    public void delete(long deletedEntry) {
        currentRepo.deleteById(deletedEntry);
        log.debug("Service->Delete");
    }

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerDto> customers = currentRepo.findAll().stream()
                .map(el -> customerAssembler.assemble(el)).collect(Collectors.toList());
        log.debug("Service->Get all");
        return customers;
    }

    @Override
    public CustomerDto getByPassport(String passport) {
        CustomerDto customer = customerAssembler.assemble(currentRepo.findCustomerByPassport(passport));
        log.debug("Service->Get by passport");
        return customer;
    }
}

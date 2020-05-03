package org.mycode.service;

import org.mycode.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void create(CustomerDto model);

    CustomerDto getById(long readID);

    void update(CustomerDto updatedModel);

    void delete(long deletedEntry);

    List<CustomerDto> getAll();
}

package org.mycode.service;

import org.mycode.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    void create(EmployeeDto model);

    EmployeeDto getById(long readID);

    void update(EmployeeDto updatedModel);

    void delete(long deletedEntry);

    List<EmployeeDto> getAll();
}

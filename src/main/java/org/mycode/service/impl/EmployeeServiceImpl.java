package org.mycode.service.impl;

import org.apache.log4j.Logger;
import org.mycode.assembler.EmployeeAssembler;
import org.mycode.dto.EmployeeDto;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.repository.EmployeeRepository;
import org.mycode.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = Logger.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository currentRepo;
    private EmployeeAssembler employeeAssembler;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository currentRepo, EmployeeAssembler employeeAssembler) {
        this.currentRepo = currentRepo;
        this.employeeAssembler = employeeAssembler;
    }

    @Override
    public void create(EmployeeDto model) {
        currentRepo.save(employeeAssembler.assemble(model));
        log.debug("Service->Create");
    }

    @Override
    public EmployeeDto getById(long readID) {
        EmployeeDto employee = employeeAssembler.assemble(currentRepo.findById(readID).get());
        log.debug("Service->Read");
        return employee;
    }

    @Override
    public void update(EmployeeDto updatedModel) {
        if (!currentRepo.findById(updatedModel.getId()).isPresent()) {
            throw new NoSuchEntryException();
        }
        currentRepo.save(employeeAssembler.assemble(updatedModel));
        log.debug("Service->Update");
    }

    @Override
    public void delete(long deletedEntry) {
        currentRepo.deleteById(deletedEntry);
        log.debug("Service->Delete");
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeDto> employees = StreamSupport.stream(currentRepo.findAll().spliterator(), false)
                .map(el -> employeeAssembler.assemble(el)).collect(Collectors.toList());
        log.debug("Service->Get all");
        return employees;
    }
}

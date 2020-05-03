package org.mycode.service.impl;

import org.apache.log4j.Logger;
import org.mycode.assembler.ServiceAssembler;
import org.mycode.dto.ServiceDto;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.repository.ServiceRepository;
import org.mycode.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceServiceImpl implements ServiceService {
    private static final Logger log = Logger.getLogger(ServiceServiceImpl.class);
    private ServiceRepository currentRepo;
    private ServiceAssembler serviceAssembler;

    @Autowired
    public ServiceServiceImpl(ServiceRepository currentRepo, ServiceAssembler serviceAssembler) {
        this.currentRepo = currentRepo;
        this.serviceAssembler = serviceAssembler;
    }

    @Override
    public void create(ServiceDto model) {
        currentRepo.save(serviceAssembler.assemble(model));
        log.debug("Service->Create");
    }

    @Override
    public ServiceDto getById(long readID) {
        ServiceDto service = serviceAssembler.assemble(currentRepo.findById(readID).get());
        log.debug("Service->Read");
        return service;
    }

    @Override
    public void update(ServiceDto updatedModel) {
        if (!currentRepo.findById(updatedModel.getId()).isPresent()) {
            throw new NoSuchEntryException();
        }
        currentRepo.save(serviceAssembler.assemble(updatedModel));
        log.debug("Service->Update");
    }

    @Override
    public void delete(long deletedEntry) {
        currentRepo.deleteById(deletedEntry);
        log.debug("Service->Delete");
    }

    @Override
    public List<ServiceDto> getAll() {
        List<ServiceDto> services = StreamSupport.stream(currentRepo.findAll().spliterator(), false)
                .map(el -> serviceAssembler.assemble(el)).collect(Collectors.toList());
        log.debug("Service->Get all");
        return services;
    }
}

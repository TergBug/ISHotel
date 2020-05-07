package org.mycode.assembler.impl;

import org.mycode.assembler.ServiceAssembler;
import org.mycode.dto.ServiceDto;
import org.mycode.model.Service;
import org.mycode.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceAssemblerImpl implements ServiceAssembler {
    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceAssemblerImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service assemble(ServiceDto dto) {
        if (dto.getName() == null || dto.getPrice() == null) {
            return serviceRepository.findById(dto.getId()).orElse(null);
        }
        return new Service(dto.getId(), dto.getName(), dto.getPrice());
    }

    @Override
    public ServiceDto assemble(Service model) {
        return new ServiceDto(model.getId(), model.getName(), model.getPrice());
    }
}

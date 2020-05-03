package org.mycode.assembler.impl;

import org.mycode.assembler.ServiceAssembler;
import org.mycode.dto.ServiceDto;
import org.mycode.model.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceAssemblerImpl implements ServiceAssembler {
    @Override
    public Service assemble(ServiceDto dto) {
        return new Service(dto.getId(), dto.getName(), dto.getPrice());
    }

    @Override
    public ServiceDto assemble(Service model) {
        return new ServiceDto(model.getId(), model.getName(), model.getPrice());
    }
}

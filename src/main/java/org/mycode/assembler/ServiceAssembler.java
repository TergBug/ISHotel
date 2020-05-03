package org.mycode.assembler;

import org.mycode.dto.ServiceDto;
import org.mycode.model.Service;

public interface ServiceAssembler {
    Service assemble(ServiceDto dto);

    ServiceDto assemble(Service model);
}

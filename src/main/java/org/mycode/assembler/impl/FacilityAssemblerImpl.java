package org.mycode.assembler.impl;

import org.mycode.assembler.FacilityAssembler;
import org.mycode.dto.FacilityDto;
import org.mycode.model.Facility;
import org.springframework.stereotype.Component;

@Component
public class FacilityAssemblerImpl implements FacilityAssembler {
    @Override
    public Facility assemble(FacilityDto dto) {
        return new Facility(dto.getId(), dto.getName(), dto.getQuantity(), dto.getPrice());
    }

    @Override
    public FacilityDto assemble(Facility model) {
        return new FacilityDto(model.getId(), model.getName(), model.getQuantity(), model.getPrice());
    }
}

package org.mycode.assembler.impl;

import org.mycode.assembler.FacilityAssembler;
import org.mycode.dto.FacilityDto;
import org.mycode.model.Facility;
import org.mycode.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacilityAssemblerImpl implements FacilityAssembler {
    private FacilityRepository facilityRepository;

    @Autowired
    public FacilityAssemblerImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility assemble(FacilityDto dto) {
        if (dto.getId() > 0 && dto.getName() == null) {
            return facilityRepository.findById(dto.getId()).orElse(null);
        } else if (dto.getId() <= 0 && dto.getName() != null) {
            return facilityRepository.findFacilityByName(dto.getName());
        }
        return new Facility(dto.getId(), dto.getName(), dto.getQuantity(), dto.getPrice());
    }

    @Override
    public FacilityDto assemble(Facility model) {
        return new FacilityDto(model.getId(), model.getName(), model.getQuantity(), model.getPrice());
    }
}

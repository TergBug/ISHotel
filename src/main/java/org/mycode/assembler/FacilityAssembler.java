package org.mycode.assembler;

import org.mycode.dto.FacilityDto;
import org.mycode.model.Facility;

public interface FacilityAssembler {
    Facility assemble(FacilityDto dto);

    FacilityDto assemble(Facility model);
}

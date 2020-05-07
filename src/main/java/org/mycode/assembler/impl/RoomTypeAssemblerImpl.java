package org.mycode.assembler.impl;

import org.mycode.assembler.RoomTypeAssembler;
import org.mycode.dto.RoomTypeDto;
import org.mycode.model.RoomType;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeAssemblerImpl implements RoomTypeAssembler {
    @Override
    public RoomType assemble(RoomTypeDto dto) {
        return new RoomType(dto.getId(), dto.getName(), dto.getPersons());
    }

    @Override
    public RoomTypeDto assemble(RoomType model) {
        return new RoomTypeDto(model.getId(), model.getName(), model.getNumberOfPersons());
    }
}

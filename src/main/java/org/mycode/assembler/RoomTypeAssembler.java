package org.mycode.assembler;

import org.mycode.dto.RoomTypeDto;
import org.mycode.model.RoomType;

public interface RoomTypeAssembler {
    RoomType assemble(RoomTypeDto dto);

    RoomTypeDto assemble(RoomType model);
}

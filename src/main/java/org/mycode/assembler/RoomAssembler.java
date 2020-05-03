package org.mycode.assembler;

import org.mycode.dto.RoomDto;
import org.mycode.model.Room;

public interface RoomAssembler {
    Room assemble(RoomDto dto);

    RoomDto assemble(Room model);
}

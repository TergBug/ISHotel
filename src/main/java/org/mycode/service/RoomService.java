package org.mycode.service;

import org.mycode.dto.RoomDto;
import org.mycode.dto.RoomTypeDto;

import java.util.List;
import java.util.Set;

public interface RoomService {
    void create(RoomDto model);

    RoomDto getById(long readID);

    List<RoomDto> getFreeRoomsByType(RoomTypeDto roomType);

    void update(RoomDto updatedModel);

    void delete(long deletedEntry);

    List<RoomDto> getAll();

    List<RoomDto> getAllFree();

    Set<RoomTypeDto> getAllRoomTypes();
}

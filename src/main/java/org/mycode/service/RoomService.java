package org.mycode.service;

import org.mycode.dto.RoomDto;

import java.util.List;

public interface RoomService {
    void create(RoomDto model);

    RoomDto getById(long readID);

    void update(RoomDto updatedModel);

    void delete(long deletedEntry);

    List<RoomDto> getAll();
}

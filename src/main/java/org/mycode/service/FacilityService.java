package org.mycode.service;

import org.mycode.dto.FacilityDto;

import java.util.List;

public interface FacilityService {
    void create(FacilityDto model);

    FacilityDto getById(long readID);

    void update(FacilityDto updatedModel);

    void delete(long deletedEntry);

    List<FacilityDto> getAll();
}

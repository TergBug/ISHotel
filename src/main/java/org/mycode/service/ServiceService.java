package org.mycode.service;

import org.mycode.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    void create(ServiceDto model);

    ServiceDto getById(long readID);

    void update(ServiceDto updatedModel);

    void delete(long deletedEntry);

    List<ServiceDto> getAll();
}

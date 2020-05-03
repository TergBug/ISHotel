package org.mycode.service.impl;

import org.apache.log4j.Logger;
import org.mycode.assembler.RoomAssembler;
import org.mycode.dto.RoomDto;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.repository.RoomRepository;
import org.mycode.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger log = Logger.getLogger(RoomServiceImpl.class);
    private RoomRepository currentRepo;
    private RoomAssembler roomAssembler;

    @Autowired
    public RoomServiceImpl(RoomRepository currentRepo, RoomAssembler roomAssembler) {
        this.currentRepo = currentRepo;
        this.roomAssembler = roomAssembler;
    }

    @Override
    public void create(RoomDto model) {
        currentRepo.save(roomAssembler.assemble(model));
        log.debug("Service->Create");
    }

    @Override
    public RoomDto getById(long readID) {
        RoomDto room = roomAssembler.assemble(currentRepo.findById(readID).get());
        log.debug("Service->Read");
        return room;
    }

    @Override
    public void update(RoomDto updatedModel) {
        if (!currentRepo.findById(updatedModel.getId()).isPresent()) {
            throw new NoSuchEntryException();
        }
        currentRepo.save(roomAssembler.assemble(updatedModel));
        log.debug("Service->Update");
    }

    @Override
    public void delete(long deletedEntry) {
        currentRepo.deleteById(deletedEntry);
        log.debug("Service->Delete");
    }

    @Override
    public List<RoomDto> getAll() {
        List<RoomDto> rooms = StreamSupport.stream(currentRepo.findAll().spliterator(), false)
                .map(el -> roomAssembler.assemble(el)).collect(Collectors.toList());
        log.debug("Service->Get all");
        return rooms;
    }
}

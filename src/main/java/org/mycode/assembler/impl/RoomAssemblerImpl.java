package org.mycode.assembler.impl;

import org.mycode.assembler.RoomAssembler;
import org.mycode.assembler.RoomTypeAssembler;
import org.mycode.dto.RoomDto;
import org.mycode.model.Room;
import org.mycode.model.enums.RoomState;
import org.mycode.repository.EmployeeRepository;
import org.mycode.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomAssemblerImpl implements RoomAssembler {
    private EmployeeRepository employeeRepository;
    private RoomRepository roomRepository;
    private RoomTypeAssembler roomTypeAssembler;

    @Autowired
    public RoomAssemblerImpl(EmployeeRepository employeeRepository, RoomRepository roomRepository,
                             RoomTypeAssembler roomTypeAssembler) {
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
        this.roomTypeAssembler = roomTypeAssembler;
    }

    @Override
    public Room assemble(RoomDto dto) {
        if (dto.getAttendant() == null || dto.getRoomType() == null || dto.getState() == null) {
            return roomRepository.findById(dto.getId()).orElse(null);
        }
        String[] names = dto.getAttendant().split("\\s");
        return new Room(dto.getId(), roomTypeAssembler.assemble(dto.getRoomType()),
                RoomState.valueOf(dto.getState()), dto.getFloor(), dto.getPrice(),
                employeeRepository.findEmployeeByFirstNameAndLastName(names[0], names[1]));
    }

    @Override
    public RoomDto assemble(Room model) {
        return new RoomDto(model.getId(), roomTypeAssembler.assemble(model.getRoomType()), model.getState().toString(),
                model.getFloor(), model.getPrice(),
                model.getAttendant().getFirstName() + " " + model.getAttendant().getLastName());
    }
}

package org.mycode.assembler.impl;

import org.mycode.assembler.RoomAssembler;
import org.mycode.dto.RoomDto;
import org.mycode.model.Room;
import org.mycode.model.enums.RoomState;
import org.mycode.repository.EmployeeRepository;
import org.mycode.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomAssemblerImpl implements RoomAssembler {
    private RoomTypeRepository roomTypeRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public RoomAssemblerImpl(RoomTypeRepository roomTypeRepository, EmployeeRepository employeeRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Room assemble(RoomDto dto) {
        String[] names = dto.getAttendant().split("\\s");
        return new Room(dto.getId(), roomTypeRepository.findRoomTypeByName(dto.getRoomType()),
                RoomState.valueOf(dto.getState()), dto.getFloor(), dto.getPrice(),
                employeeRepository.findEmployeeByFirstNameAndLastName(names[0], names[1]));
    }

    @Override
    public RoomDto assemble(Room model) {
        return new RoomDto(model.getId(), model.getRoomType().getName(), model.getState().toString(), model.getFloor(),
                model.getPrice(), model.getAttendant().getFirstName() + " " + model.getAttendant().getLastName());
    }
}

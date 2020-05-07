package org.mycode.util;

import org.mycode.dto.RoomTypeDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamProc {
    public static final List<Integer> filterByName(Set<RoomTypeDto> input, String name) {
        return input.stream().filter(el -> el.getName().equals(name)).map(RoomTypeDto::getPersons)
                .collect(Collectors.toList());
    }

    public static final List<String> distinct(Set<RoomTypeDto> input) {
        return input.stream().map(RoomTypeDto::getName).distinct().collect(Collectors.toList());
    }
}

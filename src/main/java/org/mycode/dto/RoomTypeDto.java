package org.mycode.dto;

import java.util.Objects;

public class RoomTypeDto {
    private long id;
    private String name;
    private int persons;

    public RoomTypeDto() {
    }

    public RoomTypeDto(long id, String name, int persons) {
        this.id = id;
        this.name = name;
        this.persons = persons;
    }

    public RoomTypeDto(String name, int persons) {
        this.name = name;
        this.persons = persons;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "RoomTypeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", persons=" + persons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomTypeDto that = (RoomTypeDto) o;
        return persons == that.persons &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, persons);
    }
}

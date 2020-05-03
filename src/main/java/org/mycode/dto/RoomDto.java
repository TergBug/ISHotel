package org.mycode.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class RoomDto {
    private long id;
    private String roomType;
    private String state;
    private int floor;
    private BigDecimal price;
    private String attendant;

    public RoomDto() {
    }

    public RoomDto(long id, String roomType, String state, int floor, BigDecimal price, String attendant) {
        this.id = id;
        this.roomType = roomType;
        this.state = state;
        this.floor = floor;
        this.price = price;
        this.attendant = attendant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAttendant() {
        return attendant;
    }

    public void setAttendant(String attendant) {
        this.attendant = attendant;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", state='" + state + '\'' +
                ", floor=" + floor +
                ", price=" + price +
                ", attendant=" + attendant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return id == roomDto.id &&
                floor == roomDto.floor &&
                Objects.equals(roomType, roomDto.roomType) &&
                Objects.equals(state, roomDto.state) &&
                Objects.equals(price, roomDto.price) &&
                Objects.equals(attendant, roomDto.attendant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, state, floor, price, attendant);
    }
}

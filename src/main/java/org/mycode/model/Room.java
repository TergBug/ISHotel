package org.mycode.model;

import org.mycode.model.enums.RoomState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private RoomType roomType;
    @Enumerated(EnumType.ORDINAL)
    private RoomState state;
    private int floor;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "attendant_id")
    private Employee attendant;

    public Room() {
    }

    public Room(long id) {
        this.id = id;
    }

    public Room(RoomType roomType, RoomState state, int floor, BigDecimal price, Employee attendant) {
        this.roomType = roomType;
        this.state = state;
        this.floor = floor;
        this.price = price;
        this.attendant = attendant;
    }

    public Room(long id, RoomType roomType, RoomState state, int floor, BigDecimal price, Employee attendant) {
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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
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

    public Employee getAttendant() {
        return attendant;
    }

    public void setAttendant(Employee attendant) {
        this.attendant = attendant;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", state=" + state +
                ", floor=" + floor +
                ", price=" + price +
                ", attendant=" + attendant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                floor == room.floor &&
                Objects.equals(roomType, room.roomType) &&
                state == room.state &&
                Objects.equals(price, room.price) &&
                Objects.equals(attendant, room.attendant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, state, floor, price, attendant);
    }
}

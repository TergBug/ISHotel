package org.mycode.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room_types")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "type")
    private String name;
    @Column(name = "person_number")
    private int numberOfPersons;

    public RoomType() {
    }

    public RoomType(long id) {
        this.id = id;
    }

    public RoomType(String name, int numberOfPersons) {
        this.name = name;
        this.numberOfPersons = numberOfPersons;
    }

    public RoomType(long id, String name, int numberOfPersons) {
        this.id = id;
        this.name = name;
        this.numberOfPersons = numberOfPersons;
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

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", type='" + name + '\'' +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return id == roomType.id &&
                numberOfPersons == roomType.numberOfPersons &&
                Objects.equals(name, roomType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfPersons);
    }
}

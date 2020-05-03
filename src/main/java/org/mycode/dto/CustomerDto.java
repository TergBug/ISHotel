package org.mycode.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String passport;
    private Date startDate;
    private Date endDate;
    private BigDecimal fees;
    private String paymentType;
    private RoomDto room;
    private List<String> services;
    private Map<String, Integer> facilities;

    public CustomerDto() {
    }

    public CustomerDto(long id, String firstName, String lastName, String passport, Date startDate, Date endDate,
                       BigDecimal fees, String paymentType, RoomDto room, List<String> services,
                       Map<String, Integer> facilities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fees = fees;
        this.paymentType = paymentType;
        this.room = room;
        this.services = services;
        this.facilities = facilities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public Map<String, Integer> getFacilities() {
        return facilities;
    }

    public void setFacilities(Map<String, Integer> facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fees=" + fees +
                ", paymentType='" + paymentType + '\'' +
                ", room=" + room +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(passport, that.passport) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(fees, that.fees) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, passport, startDate, endDate, fees, paymentType, room);
    }
}

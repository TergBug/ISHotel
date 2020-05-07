package org.mycode.dto;

import java.util.Objects;

public class SimpleCustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String passport;
    private String startDate;
    private String endDate;
    private double fees;
    private String paymentType;
    private long roomId;
    private String services;
    private String facilities;

    public SimpleCustomerDto() {
    }

    public SimpleCustomerDto(long id, String firstName, String lastName, String passport, String startDate,
                             String endDate, double fees, String paymentType, long roomId, String services, String facilities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fees = fees;
        this.paymentType = paymentType;
        this.roomId = roomId;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "SimpleCustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", fees='" + fees + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", room='" + roomId + '\'' +
                ", services='" + services + '\'' +
                ", facilities='" + facilities + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleCustomerDto that = (SimpleCustomerDto) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(passport, that.passport) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(fees, that.fees) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(services, that.services) &&
                Objects.equals(facilities, that.facilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, passport, startDate, endDate, fees, paymentType, roomId, services, facilities);
    }
}

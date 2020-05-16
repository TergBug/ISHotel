package org.mycode.dto;

import java.util.Objects;

public class EmployeeDto {
    private long id;
    private String firstName;
    private String lastName;
    private String ein;
    private ServiceDto service;
    private FacilityDto facility;

    public EmployeeDto() {
    }

    public EmployeeDto(long id, String firstName, String lastName, String ein, ServiceDto service, FacilityDto facility) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ein = ein;
        this.service = service;
        this.facility = facility;
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

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }

    public FacilityDto getFacility() {
        return facility;
    }

    public void setFacility(FacilityDto facility) {
        this.facility = facility;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ein='" + ein + '\'' +
                ", service=" + service +
                ", facility=" + facility +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(ein, that.ein);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, ein);
    }
}

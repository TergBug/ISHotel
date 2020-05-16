package org.mycode.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String ein;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    public Employee() {
    }

    public Employee(long id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, String ein, Service service, Facility facility) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ein = ein;
        this.service = service;
        this.facility = facility;
    }

    public Employee(long id, String firstName, String lastName, String ein, Service service, Facility facility) {
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    @Override
    public String toString() {
        return "Employee{" +
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
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(ein, employee.ein) &&
                Objects.equals(service, employee.service) &&
                Objects.equals(facility, employee.facility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, ein, service, facility);
    }
}

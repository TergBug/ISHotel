package org.mycode.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
@NamedEntityGraph(name = "Customer.detail",
        attributeNodes = {@NamedAttributeNode("services"), @NamedAttributeNode("facilities")})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String passport;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private BigDecimal fees;
    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToMany
    @JoinTable(
            name = "customers_services",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @OrderColumn(name = "service_order")
    private List<Service> services;
    @ManyToMany
    @JoinTable(
            name = "customers_facilities",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    @OrderColumn(name = "facility_order")
    private List<Facility> facilities;

    public Customer() {
    }

    public Customer(long id) {
        this.id = id;
    }

    public Customer(String firstName, String lastName, String passport, Date startDate, Date endDate, BigDecimal fees,
                    PaymentType paymentType, Room room, List<Service> services, List<Facility> facilities) {
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

    public Customer(long id, String firstName, String lastName, String passport, Date startDate, Date endDate,
                    BigDecimal fees, PaymentType paymentType, Room room, List<Service> services,
                    List<Facility> facilities) {
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fees=" + fees +
                ", paymentType=" + paymentType +
                ", room=" + room +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(passport, customer.passport) &&
                Objects.equals(startDate, customer.startDate) &&
                Objects.equals(endDate, customer.endDate) &&
                Objects.equals(fees, customer.fees) &&
                Objects.equals(paymentType, customer.paymentType) &&
                Objects.equals(room, customer.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, passport, startDate, endDate, fees, paymentType, room);
    }
}


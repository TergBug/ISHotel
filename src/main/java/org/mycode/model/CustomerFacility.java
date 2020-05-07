package org.mycode.model;

import org.mycode.model.notmodelclasses.CustomerFacilityId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customers_facilities")
@IdClass(CustomerFacilityId.class)
public class CustomerFacility {
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Id
    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;
    private int quantity;

    public CustomerFacility() {
    }

    public CustomerFacility(Customer customer, Facility facility, int quantity) {
        this.customer = customer;
        this.facility = facility;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CustomerFacility{" +
                "customer=" + customer +
                ", facility=" + facility +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerFacility that = (CustomerFacility) o;
        return quantity == that.quantity &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(facility, that.facility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, facility, quantity);
    }
}

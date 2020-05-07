package org.mycode.model.notmodelclasses;

import org.mycode.model.Customer;
import org.mycode.model.Facility;

import java.io.Serializable;
import java.util.Objects;

public class CustomerFacilityId implements Serializable {
    private Customer customer;
    private Facility facility;

    public CustomerFacilityId() {
    }

    public CustomerFacilityId(Customer customer, Facility facility) {
        this.customer = customer;
        this.facility = facility;
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

    @Override
    public String toString() {
        return "CustomerFacilityId{" +
                "customer=" + customer +
                ", facility=" + facility +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerFacilityId that = (CustomerFacilityId) o;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(facility, that.facility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, facility);
    }
}

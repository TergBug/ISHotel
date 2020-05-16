package org.mycode.model;

import org.mycode.model.embededclasses.CustomerFacilityId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "customers_facilities")
public class CustomerFacility {
    @EmbeddedId
    private CustomerFacilityId id;
    private int quantity;

    public CustomerFacility() {
    }

    public CustomerFacility(CustomerFacilityId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CustomerFacility(Customer customer, Facility facility, int quantity) {
        this.id = new CustomerFacilityId(customer, facility);
        this.quantity = quantity;
    }

    public CustomerFacilityId getId() {
        return id;
    }

    public void setId(CustomerFacilityId id) {
        this.id = id;
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
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerFacility that = (CustomerFacility) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }
}

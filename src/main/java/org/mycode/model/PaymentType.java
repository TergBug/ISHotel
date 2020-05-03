package org.mycode.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_types")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public PaymentType() {
    }

    public PaymentType(long id) {
        this.id = id;
    }

    public PaymentType(String name) {
        this.name = name;
    }

    public PaymentType(long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType paymentType = (PaymentType) o;
        return id == paymentType.id &&
                Objects.equals(name, paymentType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

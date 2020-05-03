package org.mycode.repository;

import org.mycode.model.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @EntityGraph(value = "Customer.detail", type = EntityGraph.EntityGraphType.LOAD)
    Customer findCustomerById(long id);

    @EntityGraph(value = "Customer.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<Customer> findAll();
}

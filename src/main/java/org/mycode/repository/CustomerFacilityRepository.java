package org.mycode.repository;

import org.mycode.model.CustomerFacility;
import org.mycode.model.embededclasses.CustomerFacilityId;
import org.springframework.data.repository.CrudRepository;

public interface CustomerFacilityRepository extends CrudRepository<CustomerFacility, CustomerFacilityId> {
    CustomerFacility findCustomerFacilitiesById(CustomerFacilityId id);
}

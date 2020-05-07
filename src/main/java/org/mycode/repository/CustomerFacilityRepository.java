package org.mycode.repository;

import org.mycode.model.CustomerFacility;
import org.mycode.model.Facility;
import org.mycode.model.notmodelclasses.CustomerFacilityId;
import org.springframework.data.repository.CrudRepository;

public interface CustomerFacilityRepository extends CrudRepository<CustomerFacility, CustomerFacilityId> {
    CustomerFacility findCustomerFacilityByFacility(Facility facility);
}

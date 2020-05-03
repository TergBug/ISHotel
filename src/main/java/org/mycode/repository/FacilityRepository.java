package org.mycode.repository;

import org.mycode.model.Facility;
import org.springframework.data.repository.CrudRepository;

public interface FacilityRepository extends CrudRepository<Facility, Long> {
    Facility findFacilityByName(String name);
}

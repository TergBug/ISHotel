package org.mycode.repository;

import org.mycode.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    Service findServiceByName(String name);
}

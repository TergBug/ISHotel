package org.mycode.repository;

import org.mycode.model.PaymentType;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
    PaymentType findPaymentTypeByName(String name);
}

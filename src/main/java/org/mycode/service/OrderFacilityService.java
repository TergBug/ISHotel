package org.mycode.service;

public interface OrderFacilityService {
    void order(long customerId, long facilityId);

    void denyOrder(long customerId, long facilityId);
}

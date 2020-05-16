package org.mycode.service;

import org.mycode.dto.FacilityDto;

public interface OrderFacilityService {
    void order(String customerPassport, FacilityDto facility);

    void denyOrder(String customerPassport, FacilityDto facility);
}

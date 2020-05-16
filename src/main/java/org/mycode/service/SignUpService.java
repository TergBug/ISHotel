package org.mycode.service;

import org.mycode.dto.UserSecurityDto;

public interface SignUpService {
    void signUp(UserSecurityDto userDto);

    boolean verifyCustomerByPassport(String passport);

    boolean verifyEmployeeByEIN(String ein);
}

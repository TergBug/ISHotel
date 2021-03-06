package org.mycode.assembler;

import org.mycode.dto.CustomerDto;
import org.mycode.model.Customer;

public interface CustomerAssembler {
    Customer assemble(CustomerDto dto);

    CustomerDto assemble(Customer model);
}

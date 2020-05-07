package org.mycode.assembler;

import org.mycode.dto.CustomerDto;
import org.mycode.dto.SimpleCustomerDto;

public interface SimpleCustomerConverter {
    CustomerDto convert(SimpleCustomerDto simpleDto);

    SimpleCustomerDto convert(CustomerDto dto);
}

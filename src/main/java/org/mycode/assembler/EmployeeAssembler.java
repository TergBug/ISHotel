package org.mycode.assembler;

import org.mycode.dto.EmployeeDto;
import org.mycode.model.Employee;

public interface EmployeeAssembler {
    Employee assemble(EmployeeDto dto);

    EmployeeDto assemble(Employee model);
}

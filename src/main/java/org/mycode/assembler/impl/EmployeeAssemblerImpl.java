package org.mycode.assembler.impl;

import org.mycode.assembler.EmployeeAssembler;
import org.mycode.assembler.FacilityAssembler;
import org.mycode.assembler.ServiceAssembler;
import org.mycode.dto.EmployeeDto;
import org.mycode.dto.FacilityDto;
import org.mycode.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAssemblerImpl implements EmployeeAssembler {
    private ServiceAssembler serviceAssembler;
    private FacilityAssembler facilityAssembler;

    @Autowired
    public EmployeeAssemblerImpl(ServiceAssembler serviceAssembler, FacilityAssembler facilityAssembler) {
        this.serviceAssembler = serviceAssembler;
        this.facilityAssembler = facilityAssembler;
    }

    @Override
    public Employee assemble(EmployeeDto dto) {
        return new Employee(dto.getId(), dto.getFirstName(), dto.getLastName(),
                serviceAssembler.assemble(dto.getService()), facilityAssembler.assemble(dto.getFacility()));
    }

    @Override
    public EmployeeDto assemble(Employee model) {
        FacilityDto facilityDto = null;
        if (model.getFacility() != null) {
            facilityDto = facilityAssembler.assemble(model.getFacility());
        }
        return new EmployeeDto(model.getId(), model.getFirstName(), model.getLastName(),
                serviceAssembler.assemble(model.getService()), facilityDto);
    }
}

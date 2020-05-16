package org.mycode.assembler.impl;

import org.mycode.assembler.EmployeeAssembler;
import org.mycode.assembler.FacilityAssembler;
import org.mycode.assembler.ServiceAssembler;
import org.mycode.dto.EmployeeDto;
import org.mycode.dto.FacilityDto;
import org.mycode.dto.ServiceDto;
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
        return new Employee(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEin(),
                serviceAssembler.assemble(dto.getService()), facilityAssembler.assemble(dto.getFacility()));
    }

    @Override
    public EmployeeDto assemble(Employee model) {
        FacilityDto facilityDto = null;
        if (model.getFacility() != null) {
            facilityDto = facilityAssembler.assemble(model.getFacility());
        }
        ServiceDto serviceDto = null;
        if (model.getService() != null) {
            serviceDto = serviceAssembler.assemble(model.getService());
        }
        return new EmployeeDto(model.getId(), model.getFirstName(), model.getLastName(), model.getEin(),
                serviceDto, facilityDto);
    }
}

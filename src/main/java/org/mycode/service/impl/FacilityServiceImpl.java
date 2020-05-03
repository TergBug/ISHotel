package org.mycode.service.impl;

import org.apache.log4j.Logger;
import org.mycode.assembler.FacilityAssembler;
import org.mycode.dto.FacilityDto;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.repository.FacilityRepository;
import org.mycode.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FacilityServiceImpl implements FacilityService {
    private static final Logger log = Logger.getLogger(FacilityServiceImpl.class);
    private FacilityRepository currentRepo;
    private FacilityAssembler facilityAssembler;

    @Autowired
    public FacilityServiceImpl(FacilityRepository currentRepo, FacilityAssembler facilityAssembler) {
        this.currentRepo = currentRepo;
        this.facilityAssembler = facilityAssembler;
    }

    @Override
    public void create(FacilityDto model) {
        currentRepo.save(facilityAssembler.assemble(model));
        log.debug("Service->Create");
    }

    @Override
    public FacilityDto getById(long readID) {
        FacilityDto facility = facilityAssembler.assemble(currentRepo.findById(readID).get());
        log.debug("Service->Read");
        return facility;
    }

    @Override
    public void update(FacilityDto updatedModel) {
        if (!currentRepo.findById(updatedModel.getId()).isPresent()) {
            throw new NoSuchEntryException();
        }
        currentRepo.save(facilityAssembler.assemble(updatedModel));
        log.debug("Service->Update");
    }

    @Override
    public void delete(long deletedEntry) {
        currentRepo.deleteById(deletedEntry);
        log.debug("Service->Delete");
    }

    @Override
    public List<FacilityDto> getAll() {
        List<FacilityDto> facilities = StreamSupport.stream(currentRepo.findAll().spliterator(), false)
                .map(el -> facilityAssembler.assemble(el)).collect(Collectors.toList());
        log.debug("Service->Get all");
        return facilities;
    }
}

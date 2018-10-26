package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.Office;
import com.nearsoft.tbwlogistics.repository.OfficeRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OfficeService {

    private static Logger logger = Logger.getLogger(OfficeService.class.getName());

    private OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> findByName(String name) {
        List<Office> officeList = new ArrayList<>();

        try {
            officeList = officeRepository.findByNameIgnoreCaseContaining(name);
        } catch (InvalidDataAccessApiUsageException e) {
            logger.warning("officeRepository.findByNameIgnoreCaseContaining() was called with null argument");
        }

        return officeList;
    }

    public Office findById(Long id) {
        Office office = null;

        try {
            office = officeRepository.findById(id).orElse(null);
        } catch (InvalidDataAccessApiUsageException e) {
            logger.warning("officeRepository.findById() was called with null argument");
        }

        return office;
    }
}

package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.Office;
import com.nearsoft.tbwlogistics.repository.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    private OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> findByName(String name) {
        return officeRepository.findByNameIgnoreCaseContaining(name);
    }

    public Office findById(Long id) {
        return officeRepository.findById(id).orElse(null);
    }
}

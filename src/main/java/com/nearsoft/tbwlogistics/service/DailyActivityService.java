package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.DailyActivity;
import com.nearsoft.tbwlogistics.repository.DailyActivityRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DailyActivityService {

    private static Logger logger = Logger.getLogger(DailyActivityService.class.getName());

    private DailyActivityRepository dailyActivityRepository;

    public DailyActivityService(DailyActivityRepository dailyActivityRepository) {
        this.dailyActivityRepository = dailyActivityRepository;
    }

    public List<DailyActivity> findByName(String name) {
        List<DailyActivity> dailyActivities = new ArrayList<>();

        try {
            dailyActivities = dailyActivityRepository.findByNameIgnoreCaseContaining(name);
        } catch (InvalidDataAccessApiUsageException e) {
            logger.warning("dailyActivities.findByNameIgnoreCaseContaining() was called with null argument");
        }

        return dailyActivities;
    }

    public DailyActivity findById(Long id) {
        DailyActivity dailyActivity = null;

        try {
            dailyActivity = dailyActivityRepository.findById(id).orElse(null);
        } catch (InvalidDataAccessApiUsageException e) {
            logger.warning("dailyActivityRepository.findById() was called with null argument");
        }

        return dailyActivity;
    }
}

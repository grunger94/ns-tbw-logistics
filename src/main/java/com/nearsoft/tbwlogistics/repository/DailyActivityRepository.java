package com.nearsoft.tbwlogistics.repository;

import com.nearsoft.tbwlogistics.entity.DailyActivity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyActivityRepository extends CrudRepository<DailyActivity, Long> {

    List<DailyActivity> findByNameIgnoreCaseContaining(String name);
}

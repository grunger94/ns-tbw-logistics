package com.nearsoft.tbwlogistics.repository;

import com.nearsoft.tbwlogistics.entity.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficeRepository extends CrudRepository<Office, Long> {

    List<Office> findByNameIgnoreCaseContaining(String name);
}

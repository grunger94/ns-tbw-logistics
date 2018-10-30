package com.nearsoft.tbwlogistics.repository;

import com.nearsoft.tbwlogistics.entity.DailyActivity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyActivityRepositoryTest {

    @Autowired
    private DailyActivityRepository dailyActivityRepository;

    @Test
    public void findById() {
        Optional<DailyActivity> dailyActivityOptional = dailyActivityRepository.findById(1L);
        Assert.assertNotNull(dailyActivityOptional);
        Assert.assertTrue(dailyActivityOptional.isPresent());

        DailyActivity dailyActivity = dailyActivityOptional.get();
        Assert.assertEquals(dailyActivity.getId(), new Long (1));
        Assert.assertEquals(dailyActivity.getName(), "Carnita asada");
        Assert.assertEquals(dailyActivity.getDescription(), "Nos reunimos a la llegada de los foraneos para una carne asada");
        Assert.assertNotNull(dailyActivity.getDate());
        Assert.assertEquals(dailyActivity.getDurationInHours(), 4);
        Assert.assertEquals(dailyActivity.getPlace(), "La mansion");
        Assert.assertEquals(dailyActivity.getAttendanceLimit(), 0);
    }

    @Test
    public void findByNotExistingId() {
        Optional<DailyActivity> optionalDailyActivity = dailyActivityRepository.findById(999L);
        Assert.assertNotNull(optionalDailyActivity);
        Assert.assertFalse(optionalDailyActivity.isPresent());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findByNullId() {
        dailyActivityRepository.findById(null);
    }

    @Test
    public void findByName() {
        List<DailyActivity> dailyActivities = dailyActivityRepository.findByNameIgnoreCaseContaining("asada");
        Assert.assertNotNull(dailyActivities);
        Assert.assertFalse(dailyActivities.isEmpty());
        Assert.assertEquals(dailyActivities.size(), 1);
        Assert.assertNotNull(dailyActivities.get(0));
    }

    @Test
    public void findByNotExistingName() {
        List<DailyActivity> dailyActivities = dailyActivityRepository.findByNameIgnoreCaseContaining("actividad sana y educativa");
        Assert.assertNotNull(dailyActivities);
        Assert.assertTrue(dailyActivities.isEmpty());
    }

    @Test
    public void findByEmptyName() {
        List<DailyActivity> dailyActivities = dailyActivityRepository.findByNameIgnoreCaseContaining("");
        Assert.assertNotNull(dailyActivities);
        Assert.assertFalse(dailyActivities.isEmpty());
        Assert.assertEquals(dailyActivities.size(), 2);

        Iterator<DailyActivity> iterator = dailyActivities.iterator();
        while (iterator.hasNext()) {
            Assert.assertNotNull(iterator.next());
        }
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findByNullName() {
        dailyActivityRepository.findByNameIgnoreCaseContaining(null);
    }
}

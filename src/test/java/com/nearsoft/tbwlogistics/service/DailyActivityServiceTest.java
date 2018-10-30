package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.DailyActivity;
import com.nearsoft.tbwlogistics.entity.Office;
import com.nearsoft.tbwlogistics.repository.DailyActivityRepository;
import com.nearsoft.tbwlogistics.repository.OfficeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyActivityServiceTest {

    @Mock
    private DailyActivityRepository dailyActivityRepositoryMock;

    @InjectMocks
    private DailyActivityService dailyActivityServiceWithMockedRepo;

    @Autowired
    private DailyActivityService dailyActivityService;

    @Test
    public void findByIdCallsRepositoryMethod() {
        dailyActivityServiceWithMockedRepo.findById(1L);
        Mockito.verify(dailyActivityRepositoryMock, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findByNotExistingId() {
        DailyActivity dailyActivity = dailyActivityService.findById(999L);
        Assert.assertNull(dailyActivity);
    }

    @Test
    public void findByNullId() {
        DailyActivity dailyActivity = dailyActivityService.findById(null);
        Assert.assertNull(dailyActivity);
    }

    @Test
    public void findByNameCallsRepositoryMethod() {
        dailyActivityServiceWithMockedRepo.findByName("CUU");
        Mockito.verify(dailyActivityRepositoryMock, Mockito.times(1)).findByNameIgnoreCaseContaining("CUU");
    }

    @Test
    public void findByNotExistingName() {
        List<DailyActivity> dailyActivities = dailyActivityService.findByName("actividad sana");
        Assert.assertNotNull(dailyActivities);
        Assert.assertTrue(dailyActivities.isEmpty());
    }

    @Test
    public void findByNullName() {
        List<DailyActivity> dailyActivities = dailyActivityService.findByName(null);
        Assert.assertNotNull(dailyActivities);
        Assert.assertTrue(dailyActivities.isEmpty());
    }

    @Test
    public void findByEmptyName() {
        List<DailyActivity> dailyActivities = dailyActivityService.findByName("");
        Assert.assertNotNull(dailyActivities);
        Assert.assertFalse(dailyActivities.isEmpty());
        Assert.assertEquals(dailyActivities.size(), 2);
    }
}

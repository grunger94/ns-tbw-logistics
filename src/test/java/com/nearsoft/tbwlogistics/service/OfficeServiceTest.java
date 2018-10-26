package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.Office;
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
public class OfficeServiceTest {

    @Mock
    private OfficeRepository officeRepositoryMock;

    @InjectMocks
    private OfficeService officeServiceWithMockedRepo;

    @Autowired
    private OfficeService officeService;

    @Test
    public void findByIdCallsRepositoryMethod() {
        officeServiceWithMockedRepo.findById(1L);
        Mockito.verify(officeRepositoryMock, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findByNotExistingId() {
        Office office = officeService.findById(999L);
        Assert.assertNull(office);
    }

    @Test
    public void findByNullId() {
        Office office = officeService.findById(null);
        Assert.assertNull(office);
    }

    @Test
    public void findByNameCallsRepositoryMethod() {
        officeServiceWithMockedRepo.findByName("CUU");
        Mockito.verify(officeRepositoryMock, Mockito.times(1)).findByNameIgnoreCaseContaining("CUU");
    }

    @Test
    public void findByNotExistingName() {
        List<Office> offices = officeService.findByName("SLP");
        Assert.assertNotNull(offices);
        Assert.assertTrue(offices.isEmpty());
    }

    @Test
    public void findByNullName() {
        List<Office> offices = officeService.findByName(null);
        Assert.assertNotNull(offices);
        Assert.assertTrue(offices.isEmpty());
    }

    @Test
    public void findByEmptyName() {
        List<Office> offices = officeService.findByName("");
        Assert.assertNotNull(offices);
        Assert.assertFalse(offices.isEmpty());
        Assert.assertEquals(offices.size(), 1);
    }
}

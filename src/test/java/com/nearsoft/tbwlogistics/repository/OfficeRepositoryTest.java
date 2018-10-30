package com.nearsoft.tbwlogistics.repository;

import com.nearsoft.tbwlogistics.entity.Office;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeRepositoryTest {

    @Autowired
    private OfficeRepository officeRepository;

    @Test
    public void findById() {
        Optional<Office> optionalOffice = officeRepository.findById(1L);
        Assert.assertNotNull(optionalOffice);
        Assert.assertTrue(optionalOffice.isPresent());

        Office office = optionalOffice.get();
        Assert.assertNotNull(office);
        Assert.assertEquals(office.getId(), new Long(1));
        Assert.assertEquals(office.getName(), "CUU");
        Assert.assertNotNull(office.getPersonList());
        Assert.assertEquals(office.getPersonList().size(), 3);
        Assert.assertNotNull(office.getOfficeManagers());
        Assert.assertEquals(office.getOfficeManagers().size(), 1);
    }

    @Test
    public void findByNotExistingId() {
        Optional<Office> optionalOffice = officeRepository.findById(999L);
        Assert.assertNotNull(optionalOffice);
        Assert.assertFalse(optionalOffice.isPresent());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findByNullId() {
        officeRepository.findById(null);
    }

    @Test
    public void findByName() {
        List<Office> officeList = officeRepository.findByNameIgnoreCaseContaining("CUU");

        Assert.assertNotNull(officeList);
        Assert.assertFalse(officeList.isEmpty());
        Assert.assertEquals(officeList.size(), 1);

        Office office = officeList.get(0);

        Assert.assertEquals(office.getId(), new Long(1));
        Assert.assertEquals(office.getName(), "CUU");
        Assert.assertNotNull(office.getPersonList());
        Assert.assertEquals(office.getPersonList().size(), 3);
        Assert.assertNotNull(office.getOfficeManagers());
        Assert.assertEquals(office.getOfficeManagers().size(), 1);
    }

    @Test
    public void findByNotExistingName() {
        List<Office> officeList = officeRepository.findByNameIgnoreCaseContaining("SLP");

        Assert.assertNotNull(officeList);
        Assert.assertTrue(officeList.isEmpty());
    }

    @Test
    public void findByEmptyName() {
        List<Office> officeList = officeRepository.findByNameIgnoreCaseContaining("");

        Assert.assertNotNull(officeList);
        Assert.assertFalse(officeList.isEmpty());
        Assert.assertEquals(officeList.size(), 3);

        Office office = officeList.get(1);

        Assert.assertEquals(office.getId(), new Long(2));
        Assert.assertEquals(office.getName(), "HMO");
        Assert.assertNotNull(office.getPersonList());
        Assert.assertEquals(office.getPersonList().size(), 2);
        Assert.assertNotNull(office.getOfficeManagers());
        Assert.assertEquals(office.getOfficeManagers().size(), 2);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findByNullName() {
        officeRepository.findByNameIgnoreCaseContaining(null);
    }
}

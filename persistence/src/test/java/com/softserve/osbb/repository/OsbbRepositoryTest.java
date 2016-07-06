package com.softserve.osbb.repository;

import com.softserve.osbb.OsbbApplicationRunner;
import com.softserve.osbb.model.OsbbEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Roman on 06.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Transactional
public class OsbbRepositoryTest {

    private OsbbEntity osbbEntity;

    @Autowired
    OsbbRepository osbbRepository;

    @Before
    public void init() {
        osbbEntity = new OsbbEntity();
        osbbEntity.setName("Lviv_osbb");
        osbbEntity.setDescription("osbb for people");
        osbbEntity.setCreatorId(5);
    }

    @Before
    public void addIntoDB() {
        osbbEntity = osbbRepository.save(osbbEntity);
    }

    @Test
    public void testSave(){
        OsbbEntity savedOsbb = osbbRepository.save(osbbEntity);
        Assert.assertNotNull(savedOsbb);
        Assert.assertEquals("Lviv_osbb",savedOsbb.getName());
    }

    @Test
    public void testGetOsbbById() {
        OsbbEntity savedOsbb = osbbRepository.save(osbbEntity);
        Assert.assertEquals(osbbRepository.getOne(savedOsbb.getOsbbId()),savedOsbb);
    }

    @Test
    public void testGetAllOsbb() {
        List<OsbbEntity> list = Arrays.asList(new OsbbEntity(), new OsbbEntity(), new OsbbEntity());
        osbbRepository.deleteAll();
        osbbRepository.save(list);
        Assert.assertTrue(list.size() == osbbRepository.findAll().size());
    }

    @Test
    public void testDeleteOsbbById() {
        osbbRepository.delete(osbbEntity.getOsbbId());
        Assert.assertFalse(osbbRepository.exists(osbbEntity.getOsbbId()));
    }

    @Test
    public void testDeleteOsbbByOsbbEntity() {
        osbbRepository.delete(osbbEntity);
        Assert.assertFalse(osbbRepository.exists(osbbEntity.getOsbbId()));
    }

    @Test
    public void testDeleteAllOsbb() {
        Assert.assertNotEquals(0, osbbRepository.findAll().size());
        osbbRepository.deleteAll();
        Assert.assertEquals(0, osbbRepository.findAll().size());
    }

}
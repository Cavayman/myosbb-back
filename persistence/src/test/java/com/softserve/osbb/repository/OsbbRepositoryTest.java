package com.softserve.osbb.repository;

import com.softserve.osbb.OsbbApplicationRunner;
import com.softserve.osbb.model.Osbb;
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

    private Osbb osbb;

    @Autowired
    OsbbRepository osbbRepository;

    @Before
    public void init() {
        osbb = new Osbb();
        osbb.setName("Lviv_osbb");
        osbb.setDescription("osbb for people");
        osbb.setCreatorId(5);
    }

    @Before
    public void addIntoDB() {
        osbb = osbbRepository.save(osbb);
    }

    @Test
    public void testSave(){
        Osbb savedOsbb = osbbRepository.save(osbb);
        Assert.assertNotNull(savedOsbb);
        Assert.assertEquals("Lviv_osbb",savedOsbb.getName());
    }

    @Test
    public void testGetOsbbById() {
        Osbb savedOsbb = osbbRepository.save(osbb);
        Assert.assertEquals(osbbRepository.getOne(savedOsbb.getOsbbId()),savedOsbb);
    }

    @Test
    public void testGetAllOsbb() {
        List<Osbb> list = Arrays.asList(new Osbb(), new Osbb(), new Osbb());
        osbbRepository.deleteAll();
        osbbRepository.save(list);
        Assert.assertTrue(list.size() == osbbRepository.findAll().size());
    }

    @Test
    public void testDeleteOsbbById() {
        osbbRepository.delete(osbb.getOsbbId());
        Assert.assertFalse(osbbRepository.exists(osbb.getOsbbId()));
    }

    @Test
    public void testDeleteOsbbByOsbb() {
        osbbRepository.delete(osbb);
        Assert.assertFalse(osbbRepository.exists(osbb.getOsbbId()));
    }

    @Test
    public void testDeleteAllOsbb() {
        Assert.assertNotEquals(0, osbbRepository.findAll().size());
        osbbRepository.deleteAll();
        Assert.assertEquals(0, osbbRepository.findAll().size());
    }

}
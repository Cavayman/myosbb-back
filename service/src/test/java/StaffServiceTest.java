import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.config.ServiceApplication;
import com.softserve.osbb.model.Staff;
import com.softserve.osbb.service.StaffService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasiia Fedorak on 13.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@Rollback
@Transactional
public class StaffServiceTest {
    private Staff staff;
    List<Staff> staffs;

    @Autowired
    StaffService staffService;

    @Before
    public void init(){
        staff = new Staff();
        staffs = new ArrayList<>();
        staffs.add(staff);
    }

    @Test
    public void testSaveAndFind(){
        staffService.saveStaff(staff);
        Assert.assertNotNull(staff.getStaffId());
        staffService.saveStaffList(staffs);
        Assert.assertNotNull(staffService.findOneStaffById(staff.getStaffId()));
        Assert.assertNotNull(staffService.findAllStaffs());
    }

    @Test
    public void testDelete(){
        testSaveAndFind();
        staffService.deleteStaff(staff);
        Assert.assertNull(staffService.findOneStaffById(staff.getStaffId()));
        staffService.deleteAllStaffs();
        Assert.assertTrue(staffService.findAllStaffs().isEmpty());
    }

    @Test
    public void testCountAndExists(){
        testSaveAndFind();
        Assert.assertTrue(staffService.countStaffs()>=1);
        Assert.assertTrue(staffService.existsStaff(staff.getStaffId()));
    }


}

package cn.org.bnuz.it.bloodms.dao;

import cn.org.bnuz.it.bloodms.model.Donor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试DonorDao所有方法
 * 1. findById
 * 2. findByIdAndPwd
 * 3. listPaged
 * 4. saveOrUpdate
 * 5. delete
 * 测试全通过 2014-03-17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class DonorDaoTest {

    @Autowired
    DonorDao donorDao;

    @Test
    public void testFindByIdAndPwd() {
        Donor donor = new Donor();
        donor.setId(5000);
        donor.setName("young");
        donor.setPwd("123");
        donorDao.saveOrUpdate(donor);
        Donor found = donorDao.findByIdAndPwd(5000, "123");
        assert found.getId() == donor.getId();
    }

    @Test
    public void testListPaged() {
        testAdd();
        List<Donor> donors = donorDao.listPaged(1,20);
        assert donors.size() == 20;
    }

    @Test
    public void testDelete() {
        Donor donor = new Donor();
        donor.setId(5000);
        donor.setName("young");
        donorDao.saveOrUpdate(donor);
        donorDao.delete(5000);
        Donor deleted = donorDao.findById(5000);
        assert deleted == null;
    }

    @Test
    public void testAdd() {
        List<Donor> list = createDonors(50);
        for(Donor donor : list) {
            donorDao.saveOrUpdate(donor);
        }
        Donor admin = new Donor();
        admin.setId(440);
        admin.setName("young");
        admin.setPwd("123");
        donorDao.saveOrUpdate(admin);

        Donor found = donorDao.findById(440);
        assert found.getId() == admin.getId();
    }


    private static List<Donor> createDonors(int count) {
        Random random = new Random();
        List<Donor> list = new ArrayList<Donor>();
        for (int i = 0; i < count; i++) {
            Donor donor = new Donor();
            donor.setId(random.nextInt(count * count));
            donor.setName("name" + random.nextInt(count * count));
            list.add(donor);
        }
        return list;
    }
}

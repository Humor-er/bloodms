package cn.org.bnuz.it.bloodms.service;

import cn.org.bnuz.it.bloodms.dao.DonorDao;
import cn.org.bnuz.it.bloodms.model.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *捐献者登陆系统
 *业务简单,无需测试
 */
@Service
public class DonorLoginService {
    @Autowired
    private DonorDao donorDao;

    public Donor login(long id, String pwd) {
        return donorDao.findByIdAndPwd(id, pwd);
    }
}

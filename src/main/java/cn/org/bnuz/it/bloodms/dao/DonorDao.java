package cn.org.bnuz.it.bloodms.dao;

import cn.org.bnuz.it.bloodms.model.Donor;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class DonorDao extends Dao {

    public Donor findById(long id) {
        String hql = "FROM Donor d WHERE d.id = ?";
        Query query = query(hql, id);
        List<Donor> list = Collections.checkedList(query.list(), Donor.class);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
    /**
     * 通过身份证和密码查找用户,用于登陆验证
     * @param id 身份证
     * @param pwd 密码
     * @return
     */
    public Donor findByIdAndPwd(long id, String pwd) {
        String hql = "FROM Donor d WHERE d.id = ? AND d.pwd = ?";
        Query query = query(hql, id, pwd);
        List<Donor> list = Collections.checkedList(query.list(), Donor.class);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public List<Donor> listPaged(int now, int total) {
        String hql = "FROM Donor";
        Query query = query(hql);
        query.setFirstResult(now);
        query.setMaxResults(total);
       return Collections.checkedList(query.list(), Donor.class);
    }

    public void saveOrUpdate(Donor donor) {
        session().saveOrUpdate(donor);
    }

    public boolean delete(long id) {
        String hql = "DELETE Donor d WHERE d.id = ?";
        Query query = query(hql, id);
        int effect = query.executeUpdate();
        return effect > 0 ? true : false;
    }
}

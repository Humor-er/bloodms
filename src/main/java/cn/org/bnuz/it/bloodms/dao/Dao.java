package cn.org.bnuz.it.bloodms.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Dao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    protected Query query(String hql,Object... params) {
        Query query = session().createQuery(hql);
        int len = params.length;
        for(int i = 0; i < len; i++) {
            query.setParameter(i, params[i]);
        }
        return query;
    }
}

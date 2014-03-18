package cn.org.bnuz.it.bloodms.dao;

import cn.org.bnuz.it.bloodms.model.News;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class NewsDao extends Dao {

    public void saveOrUpdate(News news) {
        session().saveOrUpdate(news);
    }

    public boolean delete(int id) {
        String hql = "DELETE News n WHERE n.id = ?";
        Query query = query(hql, id);
        int effect = query.executeUpdate();
        return effect > 0 ? true : false;
    }

    public List<News> listPaged(int now, int total) {
        String hql = "FROM News";
        Query query = query(hql);
        query.setFirstResult(now);
        query.setMaxResults(total);
        return Collections.checkedList(query.list(), News.class);
    }

    public News findById(int id) {
        String hql = "FROM News n WHERE n.id = ?";
        Query query = query(hql, id);
        List<News> list = Collections.checkedList(query.list(), News.class);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}

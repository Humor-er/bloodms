package cn.org.bnuz.it.bloodms.dao;

import cn.org.bnuz.it.bloodms.model.Donor;
import cn.org.bnuz.it.bloodms.model.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试NewsDao所有方法
 * 1. findById
 * 2. listPaged
 * 3. saveOrUpdate
 * 4. delete
 * 测试全通过 2014-03-18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class NewsDaoTest {

    @Autowired
    NewsDao newsDao;

    @Test
    public void testFindById() {
        News news = new News();
        news.setContent("news1");
        newsDao.saveOrUpdate(news);
        News found = newsDao.findById(news.getId());
        assert found.getId() == news.getId();
    }

    @Test
    public void testListPaged() {
        testAdd();
        List<News> news = newsDao.listPaged(1,20);
        assert news.size() == 20;
    }

    @Test
    public void testDelete() {
        News news = new News();
        news.setContent("test");
        newsDao.saveOrUpdate(news);
        int id = news.getId();
        newsDao.delete(id);
        News deleted = newsDao.findById(id);
        assert deleted == null;
    }

    @Test
    public void testAdd() {
        List<News> list = createNews(50);
        for(News news : list) {
            newsDao.saveOrUpdate(news);
        }
        List<News> news = newsDao.listPaged(0, 50);
        assert news.size() == 50;
    }


    private static List<News> createNews(int count) {
        Random random = new Random();
        List<News> list = new ArrayList<News>();
        for (int i = 0; i < count; i++) {
            News news = new News();
            news.setContent("news" + random.nextInt(1000));
            list.add(news);
        }
        return list;
    }
}

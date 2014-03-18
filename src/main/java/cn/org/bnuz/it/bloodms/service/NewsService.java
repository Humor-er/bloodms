package cn.org.bnuz.it.bloodms.service;

import cn.org.bnuz.it.bloodms.dao.NewsDao;
import cn.org.bnuz.it.bloodms.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<News> listPaged(int now, int total) {
        return newsDao.listPaged(now, total);
    }

}

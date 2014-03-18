package cn.org.bnuz.it.bloodms.controller;

import cn.org.bnuz.it.bloodms.dao.NewsDao;
import cn.org.bnuz.it.bloodms.model.News;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class NewsControllerTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private NewsDao newsDao;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testFetchNews() throws Exception {
        List<News> list = createNews(50);
        for(News news : list) {
            newsDao.saveOrUpdate(news);
        }
        MvcResult result =  mockMvc.perform(get("/fetchNews.do/1").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
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

package cn.org.bnuz.it.bloodms.controller;

import cn.org.bnuz.it.bloodms.model.News;
import cn.org.bnuz.it.bloodms.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理与新闻有关的请求
 */
@Controller
@RequestMapping("/")
public class NewsController {

    private static final int TOTAL_IN_ONE_PAGE = 4;

    @Autowired
    private NewsService newsService;

    /**
     * 异步获取新闻列表,返回Json数据
     * {"news":[],"activities":[]}
     * @param currentPage 当前页码
     * @return
     */
    @RequestMapping(value = "fetchNews.do/{currentPage}", produces="application/json")
    public @ResponseBody Map<String, List<News>> fetchNews(@PathVariable("currentPage") int currentPage) {
        List<News> activities = newsService.listPaged(currentPage, TOTAL_IN_ONE_PAGE);
        List<News> news = newsService.listPaged(currentPage, TOTAL_IN_ONE_PAGE);
        Map<String, List<News>> map = new HashMap<String, List<News>>();
        map.put("activities", activities);
        map.put("news", news);
        return map;
    }
}

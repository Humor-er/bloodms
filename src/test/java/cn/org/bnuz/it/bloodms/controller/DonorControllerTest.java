package cn.org.bnuz.it.bloodms.controller;

import cn.org.bnuz.it.bloodms.dao.DonorDao;
import cn.org.bnuz.it.bloodms.model.Donor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class DonorControllerTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private DonorDao donorDao;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testLogin() throws Exception {
        long id = 440;
        String pwd = "123";
        Donor admin = new Donor();
        admin.setId(id);
        admin.setPwd(pwd);
        donorDao.saveOrUpdate(admin);
        mockMvc.perform(post("/login.do").param("id", id + "").param("pwd",pwd))
                .andExpect(status().isOk())         // 200Http状态吗
                .andExpect(view().name("index"));  // 返回页面index.html
    }

}

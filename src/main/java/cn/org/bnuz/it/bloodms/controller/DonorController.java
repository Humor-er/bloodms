package cn.org.bnuz.it.bloodms.controller;

import cn.org.bnuz.it.bloodms.model.Donor;
import cn.org.bnuz.it.bloodms.service.DonorLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *donor的所有操作的控制器
 */
@Controller
@RequestMapping("/")
public class DonorController extends BaseController {

    @Autowired
    private DonorLoginService donorLoginService;

    /**
     * 处理登陆请求
     * @param request
     * @param id
     * @param pwd
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam("id") long id, @RequestParam("pwd") String pwd) {
        // 调用登陆业务
        Donor donor = donorLoginService.login(id, pwd);
        if(donor != null) {
            // 登陆成功，把已验证的donor对象存放到session中
            request.getSession().setAttribute("donor", donor);
            // 重定向到index.do
            return "index";
        }
        // 登陆不成功，返回登陆页面
        return "donor/login.html";
    }
}

package cn.org.bnuz.it.bloodms.controller;

import cn.org.bnuz.it.bloodms.model.Donor;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected static final String ERROR_MSG_KEY = "errorMsg";

    protected boolean loginSuccess(HttpServletRequest request) {
        return request.getSession().getAttribute("donor") != null ? true : false;
    }

    protected Donor getSessionUser(HttpServletRequest request){
        return (Donor) request.getSession().getAttribute("USER_CONTEXT");
    }

    protected void setSessionUser(HttpServletRequest request, Donor user){
        request.getSession().setAttribute("USER_CONTEXT", user);
    }

    public final String getAppbaseUrl(HttpServletRequest request , String url){
        return request.getContextPath();
    }
}

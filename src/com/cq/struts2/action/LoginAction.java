package com.cq.struts2.action;

import com.cq.dao.Impl.UserDaoImpl;
import com.cq.persistence.TbUser;
import com.cq.util.FinalString;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by admin on 2016/5/28.
 */
public class LoginAction extends BaseAction implements ServletRequestAware {
    private String userName ;	// 用户名
    private String password ;   // 密码
    private HttpServletRequest request;
    private int currPage;


    //getter setter 方法
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    @Override
    public String execute() throws Exception {
        TbUser user = null;
        // 查询用户
//        System.out.println("LoginAction");
        try {
            if (userName != null && password != null) {
                UserDaoImpl userDaoImpl = new UserDaoImpl();
                user = userDaoImpl.login(userName, password);
            }
            // 查询到用户则登录成功，否则登录失败返回到登录页面
            if (user != null) {
                request.getSession().setAttribute("user", user);
                return SUCCESS;
            } else {
                request.getSession().setAttribute("error", "error");
                return FinalString.LOGIN_FAIL;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SUCCESS;
        }

    }

    /**
     * 退出登录
     * @return
     * @throws Exception
     */
    public String quit() throws Exception {
        request.getSession().removeAttribute("user");
        return SUCCESS;
    }

    /**
     *
     * @return
     */
    public String goIndex (){
        String currPage = this.currPage+"";
        String action = request.getContextPath()+"goIndex.do?";
        String hql = "from ViewCountsell";
        //分页查询
        Map map = this.getPage(hql, recPerPage, currPage, action, null);
        //将结果集放到request中
        request.getSession().setAttribute("index_list", map.get("list"));
        //将结果集放到分页条中
        request.getSession().setAttribute("index_pagingBar", map.get("bar"));
        return SUCCESS;
    }
}

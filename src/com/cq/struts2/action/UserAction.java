package com.cq.struts2.action;

import com.cq.dao.Impl.UserDaoImpl;
import com.cq.persistence.TbUser;
import com.cq.util.FinalString;

import java.util.Map;

/**
 * Created by admin on 2016/5/31.
 */
public class UserAction extends BaseAction{
    private UserDaoImpl userDaoImpl;   //spring 之后用spring 作为容器， 要修改下面的code
    private String id ;
    private int currPage;
    private TbUser tbUser;



    public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    /**
     * @return
     * @throws Exception
     */
    public String listUser() throws Exception {
        String currPage = this.currPage+"";
        String action = request.getContextPath()+"UserMnt.do?";
        String hql = "from TbUser";
        //分页查询
        Map map = this.getPage(hql, recPerPage, currPage, action, null);
        //将结果集放到request中
        request.getSession().setAttribute("list", map.get("list"));
        //将结果集放到分页条中
        request.getSession().setAttribute("pagingBar", map.get("bar"));
//        System.out.println(map.get("bar"));
        return "success";

    }

    /**
     * @return
     */
    public String paging(){
        String currPage = request.getParameter("currPage");
        String action = request.getContextPath()+"/baseData/category.do?command=paging";
        String hql = "from Category";
        //分页查询
        Map map = this.getPage(hql, recPerPage, currPage, action,null);
        //将结果集放到request中
        request.setAttribute("list", map.get("list"));
        //将结果集放到分页条中
        request.setAttribute("pagingBar", map.get("bar"));
        return "success";
    }

    /** spring
     * @return
     */
    public String unlock() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute(FinalString.USER);
        if(tbUser.getPower().equals(FinalString.SUPER)){
            userDaoImpl = new UserDaoImpl();
          int tmpint =   userDaoImpl.unlock(Integer.parseInt(this.id));
            if(tmpint == 0){
                listUser();
                return SUCCESS;
            }
            if(tmpint == 2){
                request.getSession().removeAttribute("error");
                request.getSession().setAttribute(ERROR, FinalString.SUPER_CANT_MODIFY);
                return ERROR;
            }
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;
    }

    /**
     * @return
     */
    public String lock() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            userDaoImpl = new UserDaoImpl();
            int tmpint =   userDaoImpl.lock(Integer.parseInt(this.id));
            if(tmpint == 0){
                listUser();
                return SUCCESS;
            }
            if(tmpint == 2){
                request.getSession().removeAttribute("error");
                request.getSession().setAttribute(ERROR, FinalString.SUPER_CANT_MODIFY);
                return ERROR;
            }
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;
    }

    /**
     * @return
     */
    public String addUser(){
//        System.out.println("*********"+tbUser.getUsername()+"  "+tbUser.getPassword()+"*********" );
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            userDaoImpl = new UserDaoImpl();
           String tmpstring = userDaoImpl.addUser(this.tbUser);
            if(tmpstring.equals(FinalString.SUCCESS)){
                return SUCCESS;
            }
            else {
                request.getSession().setAttribute("account_exit", FinalString.ACCOUNT_EXIST);
                return ERROR;
            }
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;
    }

}

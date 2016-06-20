package com.cq.struts2.action;

import com.cq.dao.Impl.AnnounceDaoImpl;
import com.cq.persistence.TbAnnouncement;
import com.cq.persistence.TbUser;
import com.cq.service.Interface.AnnounceService;
import com.cq.util.FinalString;
import com.cq.util.TransDateTime;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/6/13.
 */
public class AnnounceAction extends BaseAction{
    public static final String ANNOUNCE_LIST = "announce_list";
    public static final String ANNOUNCE_PAGING_BAR = "announce_pagingBar";

    private int id ;    //用于删除公告的
    private int currPage;
    private TbAnnouncement announcement;
    private AnnounceService announceService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public TbAnnouncement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(TbAnnouncement announcement) {
        this.announcement = announcement;
    }

    public AnnounceService getAnnounceService() {
        return announceService;
    }

    public void setAnnounceService(AnnounceService announceService) {
        this.announceService = announceService;
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    /**
     * @return
     */
    public String paging(){
        String currPage = this.currPage+"";
        String action = request.getContextPath()+"AnnounceMnt.do?";
        String hql = "from TbAnnouncement";
        Map map = this.getPage(hql, recPerPage, currPage, action, null);
        //分页查询
        //将结果集放到request中
        request.getSession().setAttribute(ANNOUNCE_LIST, map.get("list"));
        //将结果集放到分页条中
        request.getSession().setAttribute(ANNOUNCE_PAGING_BAR, map.get("bar"));
        return SUCCESS;
    }

    /**
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            announcement.setCreateUserId(tbUser.getId());  // 把当前登陆账号的id 赋值
            announcement.setCreateTime(TransDateTime.transDateTime(new Date()));  //时间 赋值
            announcement.setHot(0);
            announceService.addAnnounce(announcement);
            paging();
            return SUCCESS;
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;

    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            announceService.deleteAnnounceByAnnounceId(id);
            paging();
            return SUCCESS;
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;
    }

    /**
     * @return
     * @throws Exception
     */
    public String hot() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            announceService.updatehot(id);
            paging();
            return SUCCESS;
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;
    }
    /**
     * @return
     * @throws Exception
     */
    public String show() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser != null ){
            List<TbAnnouncement> list = (List) request.getSession().getAttribute("announce_list");
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                TbAnnouncement announcement = (TbAnnouncement) iterator.next();
                if(announcement.getId() == id){
                    request.getSession().setAttribute("announce_show", announcement);
                }
            }

            return SUCCESS;
        }
        return ERROR;
    }


}

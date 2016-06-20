package com.cq.struts2.action;

import com.cq.dao.Impl.CategoryDaoImpl;
import com.cq.persistence.TbCategory;
import com.cq.persistence.TbUser;
import com.cq.util.FinalString;

import java.util.Map;

/**
 * Created by admin on 2016/5/29.
 */
public class CategoryAction extends BaseAction{
    private CategoryDaoImpl categoryDaoImpl;   //spring 之后用spring 作为容器， 要修改下面的code
    protected int recPerPage = 10; // 分页中每页的记录数
    private int currPage;
    private TbCategory tbCategory;
    private int id;

    //getter setter
    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public TbCategory getTbCategory() {
        return tbCategory;
    }

    public void setTbCategory(TbCategory tbCategory) {
        this.tbCategory = tbCategory;
    }

    public CategoryDaoImpl getCategoryDaoImpl() {
        return categoryDaoImpl;
    }

    public void setCategoryDaoImpl(CategoryDaoImpl categoryDaoImpl) {
        this.categoryDaoImpl = categoryDaoImpl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 添加类别
     * @return
     */
    //添加或修改类别
    public String add(){
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            categoryDaoImpl = new CategoryDaoImpl();
            String tmpstring = categoryDaoImpl.addCategory(this.tbCategory);
            if(tmpstring.equals(FinalString.SUCCESS)){
                paging();
                return SUCCESS;
            }
            else {
                request.getSession().setAttribute("exit", FinalString.TYPE_EXIST);
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
    //查询类别
    public  String findAll(){
        return null;
    }

    /**
     * 编辑类别
     * @return
     */
    //编辑类别
    public String edit(){
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            categoryDaoImpl = new CategoryDaoImpl();
            try {
                categoryDaoImpl.updateCategory(tbCategory);
                paging();
                return SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
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
    //删除类别
    public String delete(){
//        System.out.println(this.id);
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            categoryDaoImpl = new CategoryDaoImpl();
            try {

                categoryDaoImpl.deleteCategory(this.id);
                paging();
                return SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;

    }

    public static void main(String[] args){
        CategoryAction tmp = new CategoryAction();
        tmp.paging();
    }
    /**
     * 分页
     * @return
     */
    //分页
    public String paging(){
        String currPage = this.currPage+"";
        String action = request.getContextPath()+"LoadCategory.do?";
        String hql = "from ViewCategory";
        //分页查询
        Map map = this.getPage(hql, recPerPage, currPage, action, null);
        //将结果集放到request中
        request.getSession().setAttribute("category_list", map.get("list"));
        //将结果集放到分页条中
        request.getSession().setAttribute("category_pagingBar", map.get("bar"));
        return "success";
    }

    /**
     * @return
     */
    // 统计药品类别数量
    public String findCategoryAndCound(){
        return null;
    }

    /**
     * 到edit 页面
     * @return
     */
    public String toEdit(){
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            categoryDaoImpl = new CategoryDaoImpl();
            TbCategory category = categoryDaoImpl.findById(this.id);
            if(category != null){
                request.getSession().setAttribute(FinalString.CATEGORY_EDIT, category);
                return SUCCESS;
            }
            else {
            }
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return SUCCESS;
    }
}

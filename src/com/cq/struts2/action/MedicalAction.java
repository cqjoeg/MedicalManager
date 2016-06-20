package com.cq.struts2.action;

import com.cq.dao.Impl.CategoryDaoImpl;
import com.cq.dao.Impl.MedicalDaoImpl;
import com.cq.persistence.TbMedicine;
import com.cq.persistence.TbUser;
import com.cq.util.FinalString;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/6/10.
 */
public class MedicalAction extends BaseAction{
    protected int recPerPage = 10; // 分页中每页的记录数
    private int currPage;
    private CategoryDaoImpl categoryDaoImpl;
    private MedicalDaoImpl medicalDaoImpl;
    private TbMedicine tbMedicine;
    private String select ;
    private int categoryId;
    private String name;        // 搜索 时候使用的
    private int id;             // 编辑 时候使用的

    // getter setter
    public int getRecPerPage() {

        return recPerPage;
    }

    public void setRecPerPage(int recPerPage) {
        this.recPerPage = recPerPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public CategoryDaoImpl getCategoryDaoImpl() {
        return categoryDaoImpl;
    }

    public void setCategoryDaoImpl(CategoryDaoImpl categoryDaoImpl) {
        this.categoryDaoImpl = categoryDaoImpl;
    }

    public MedicalDaoImpl getMedicalDaoImpl() {
        return medicalDaoImpl;
    }

    public void setMedicalDaoImpl(MedicalDaoImpl medicalDaoImpl) {
        this.medicalDaoImpl = medicalDaoImpl;
    }

    public TbMedicine getTbMedicine() {
        return tbMedicine;
    }

    public void setTbMedicine(TbMedicine tbMedicine) {
        this.tbMedicine = tbMedicine;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 默认的execute 方法
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    /**
     * 分页
     * @return
     */
    public String paging(){
        //加载 类别
        categoryDaoImpl = new CategoryDaoImpl();
        List list = categoryDaoImpl.findByHQL("from TbCategory");
        request.getSession().setAttribute("category_list",list);
        String currPage = this.currPage+"";

        String action = null;
        String hql = null;
        Map map = null;
        if(categoryId == 0){
            action = request.getContextPath()+"LoadMedical.do?";
            hql = "from TbMedicine";
            //分页查询
            map = this.getPage(hql, recPerPage, currPage, action, null);
        } else {
            action = request.getContextPath()+"LoadMedical.do?categoryId=" + this.categoryId;
            hql = "from TbMedicine where categoryId = ?";
            map = this.getPage(hql, recPerPage, currPage, action, new Object[]{categoryId});
        }
        request.getSession().setAttribute("select_type", categoryId);
        //将结果集放到request中
        request.getSession().setAttribute("medical_list", map.get("list"));
        //将结果集放到分页条中
        request.getSession().setAttribute("medical_pagingBar", map.get("bar"));
        return SUCCESS;
    }


    /**
     * 通过药品名进行搜索
     * @return
     */
    public String search(){
        //加载 类别
        categoryDaoImpl = new CategoryDaoImpl();
        List list = categoryDaoImpl.findByHQL("from TbCategory");
        request.getSession().setAttribute("category_list",list);
        String currPage = this.currPage+"";
        String action = request.getContextPath()+"SearchMedical.do?name=" + this.name;
        String hql = "from TbMedicine where name like ?";

        //分页查询
        Map map = this.getPage(hql, recPerPage, currPage, action, new Object[]{"%"+name+"%"});
        request.getSession().setAttribute("select_type", categoryId);
        //将结果集放到request中
        request.getSession().setAttribute("medical_list", map.get("list"));
        //将结果集放到分页条中
        request.getSession().setAttribute("medical_pagingBar", map.get("bar"));
        return SUCCESS;

    }


    /**
     * 添加药品类型
     * @return
     */
    public String add(){
        this.tbMedicine.setCategoryId(Integer.parseInt(select));        // 把页面中 select 选的药品大类 ，的id 赋值
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            medicalDaoImpl = new MedicalDaoImpl();
            String str = medicalDaoImpl.addMedical(this.tbMedicine);
            if(str.equals(FinalString.SUCCESS)){
                paging();
                return SUCCESS;
            }
            else {
                request.getSession().setAttribute("exit", FinalString.MEDICAL_EXIST);
                return ERROR;
            }
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return ERROR;
    }


    /** 测试
     * @param args
     */
    public static void main(String[] args){
    }

    /**
     * 修改药
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            medicalDaoImpl = new MedicalDaoImpl();
            try {
                medicalDaoImpl.updateMedical(tbMedicine);
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
     * @throws Exception
     */
    public String toedit() throws Exception
    {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            medicalDaoImpl = new MedicalDaoImpl();
            TbMedicine medicine = medicalDaoImpl.findById(this.id);
            if(medicine != null){
                request.getSession().setAttribute(FinalString.MEDICAL_EDIT, medicine);
                return SUCCESS;
            }
        }
        request.getSession().removeAttribute("error");
        request.getSession().setAttribute(ERROR, FinalString.POWER_NOT_ENOUGH);
        return SUCCESS;
    }

    /**
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        if(tbUser.getPower().equals("super")){
            medicalDaoImpl = new MedicalDaoImpl();
            try {
                medicalDaoImpl.deleteMedical(this.id);
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

}

package com.cq.dao.Impl;

import com.cq.dao.Interface.CategoryDao;
import com.cq.hibernate.HibernateSessionFactory;
import com.cq.persistence.TbCategory;
import com.cq.util.FinalString;
import com.cq.util.TransDateTime;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * Created by admin on 2016/6/6.
 */
public class CategoryDaoImpl extends SupperDao implements CategoryDao{


    /**
     * @param category
     * @return
     */
    public synchronized String addCategory(TbCategory category){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            //先判断， 有没有账号密码重复的 ,如果有重复返回  "已经存在该账号"
            int i= session.createQuery("from TbCategory where name = ?").setString(0, category.getName()).list().size();
            if(i>0){
                trans.commit();
                return FinalString.TYPE_EXIST;
            }
            category.setCreateTime(TransDateTime.transDateTime(new Date()));
            session.save(category);
            trans.commit();
            return FinalString.SUCCESS;
        } catch (RuntimeException ex){
            ex.printStackTrace();
            trans.rollback();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return null;
    }



    /**
     * @param id
     * @return
     */
    public TbCategory findById(int id ){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            //先判断， 有没有账号密码重复的 ,如果有重复返回  "已经存在该账号"
            TbCategory category = (TbCategory) session.get(TbCategory.class, id);
            if(category == null){
                trans.commit();
                return category;
            }
            trans.commit();
            return category;
        } catch (RuntimeException ex){
            ex.printStackTrace();
            trans.rollback();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return null;
    }

    /**
     *  修改 Category
     * @param tbCategory
     * @return
     */
    public synchronized boolean updateCategory(TbCategory tbCategory){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            TbCategory category = (TbCategory) session.get(TbCategory.class, tbCategory.getId());
            if(category == null){
                trans.commit();
                return false;
            }
            category.setName(tbCategory.getName());
            category.setDescription(tbCategory.getDescription());
            session.save(category);
            trans.commit();
            return true;
        } catch (RuntimeException ex){
            ex.printStackTrace();
            trans.rollback();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return false;
    }


    /**
     * 删除 Category
     * @param id
     * @return
     */
    public synchronized boolean deleteCategory(int id){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            TbCategory category = (TbCategory) session.get(TbCategory.class, id);
            if(category == null){
                trans.commit();
                return false;
            }
            session.delete(category);
            trans.commit();
            return true;
        } catch (RuntimeException ex){
            ex.printStackTrace();
            trans.rollback();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return false;
    }




}

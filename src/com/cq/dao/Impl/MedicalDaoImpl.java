package com.cq.dao.Impl;

import com.cq.dao.Interface.MedicalDao;
import com.cq.hibernate.HibernateSessionFactory;
import com.cq.persistence.TbMedicine;
import com.cq.util.FinalString;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by admin on 2016/6/10.
 */
public class MedicalDaoImpl extends SupperDao implements MedicalDao{
    /**
     * 药品种类的添加
     * @return
     */
    public synchronized String addMedical(TbMedicine tbMedicine){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            int i= session.createQuery("from TbMedicine where name = ?").setString(0, tbMedicine.getName()).list().size();
            if(i>0){
                trans.commit();
                return FinalString.MEDICAL_EXIST;
            }
//            tbMedicine.setMedNo("0");
            tbMedicine.setMedCount(0);
            tbMedicine.setReqCount(0);
            session.save(tbMedicine);
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
     * 药品种类的 删除
     * @return
     */
    public synchronized boolean deleteMedical(int id){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            TbMedicine medicine = (TbMedicine) session.get(TbMedicine.class, id);
            if(medicine == null){
                trans.commit();
                return false;
            }
            session.delete(medicine);
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
     * 药品种类的 修改
     * @return
     */
    public synchronized boolean updateMedical(TbMedicine tbMedicine){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            TbMedicine medicine = (TbMedicine) session.get(TbMedicine.class, tbMedicine.getId());
            if(medicine == null){
                trans.commit();
                return false;
            }
            medicine.SET(tbMedicine);
            session.save(medicine);
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

    /** 根据id 找 TbMedicine
     * @param id
     * @return
     */
    public TbMedicine findById(int id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            //先判断， 有没有账号密码重复的 ,如果有重复返回  "已经存在该账号"
            TbMedicine medical = (TbMedicine) session.get(TbMedicine.class, id);
            if(medical == null){
                trans.commit();
                return medical;
            }
            trans.commit();
            return medical;
        } catch (RuntimeException ex){
            ex.printStackTrace();
            trans.rollback();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return null;
    }

}

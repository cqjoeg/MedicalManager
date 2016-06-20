package com.cq.dao.Impl;

import com.cq.dao.Interface.UserDao;
import com.cq.hibernate.HibernateSessionFactory;
import com.cq.persistence.TbUser;
import com.cq.util.FinalString;
import com.cq.util.TransDateTime;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by admin on 2016/5/28.
 */
public class UserDaoImpl extends SupperDao implements UserDao{
    public static final int TRUE = 0;
    public static final int FALSE = 1;
    public static final int IS_SUPER = 2;

    /**
     * 查询用户
     * @param userName
     * @param password
     * @return User
     */
    public TbUser login(String userName, String password){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        TbUser user = null;
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            //HQL查询语句
            String hql = "from TbUser u where u.username=? and u.password=?";
            Query query = session.createQuery(hql)		//创建Query对象
                    .setParameter(0, userName)//动态赋值
                    .setParameter(1, password);//动态赋值
            user = (TbUser) query.uniqueResult();			//返回User对象
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
        return user;
    }

    /**
     * 根据id查询用户
     * @param id
     * @return User
     */
    public TbUser loadUser(int id){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        TbUser user = null;
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            //根据id加载用户
            user = (TbUser)session.load(TbUser.class, new Integer(id));
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
        return user;
    }

    /**
     * 解锁账号被锁住的用户
     * @param id
     * @return
     */
    public synchronized int unlock(int id){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            TbUser tbUser = (TbUser) session.get(TbUser.class, id);
            if (tbUser == null) {
                trans.commit();
                return FALSE;
            }
            tbUser.setState(0);
            if(tbUser.getPower().equals("super")){
                return IS_SUPER;
            }
            session.update(tbUser);
            trans.commit();
            return TRUE;
        } catch (RuntimeException ex){
            ex.printStackTrace();
            trans.rollback();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return FALSE;
    }
    /**
     * 锁定账号
     * @param id
     * @return
     */
    public synchronized int lock(int id){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            TbUser tbUser = (TbUser) session.get(TbUser.class, id);
            if (tbUser == null) {
                trans.commit();
                return FALSE;
            }
            if(tbUser.getPower().equals("super")){
                return IS_SUPER;
            }
            tbUser.setState(1);
            session.update(tbUser);
            trans.commit();
            return TRUE;
        } catch (RuntimeException ex){
            ex.printStackTrace();
            trans.rollback();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return FALSE;
    }



    public static void main(String[] args) throws ParseException {
        TbUser tb = new TbUser();
        tb.setCreateTime(TransDateTime.transDateTime(new Date()));
        tb.setState(1);
        tb.setPower("normal");
        tb.setPassword("123");
        tb.setUsername("10@qq.com");

        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            System.out.println(userDaoImpl.addUser(tb));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 增加用户
     * @param tbUser
     * @return
     */
    public synchronized String addUser(TbUser tbUser){
        Session session = HibernateSessionFactory.getSession();
        Transaction trans = null;
        try{
            trans = session.beginTransaction();
            //先判断， 有没有账号密码重复的 ,如果有重复返回  "已经存在该账号"
            int i= session.createQuery("from TbUser where username = ?").setString(0, tbUser.getUsername()).list().size();
            if(i>0){
                trans.commit();
                return FinalString.ACCOUNT_EXIST;
            }
            tbUser.setPower("normal");
            tbUser.setCreateTime(TransDateTime.transDateTime(new Date()));
            session.save(tbUser);
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

}

package com.cq.dao.Impl;

/**
 * Created by admin on 2016/5/28.
 */
import java.util.List;

import com.cq.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 基本数据库操作类，放置操作数据库的常用方法
 * 主要用于继承
 *
 */
public class SupperDao extends HibernateDaoSupport {
    //Session对象
    protected Session session = null;
    /**
     * 保存信息
     * @param obj 对象
     */
    public void save(Object obj){
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            session.save(obj);							//保存对象
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
    }
    /**
     * 保存/更新信息
     * @param obj 对象
     */
    public void saveOrUpdate(Object obj){
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            session.saveOrUpdate(obj);					//保存或修改对象
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
    }

    /**
     * 删除信息
     * @param obj 对象
     */
    public void delete(Object obj){
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            session.delete(obj);						//删除对象
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
    }
    /**
     * 按hql查询所有信息
     * @param hql hql语句
     */
    public List findByHQL(String hql){
        List list = null;
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            list = session.createQuery(hql)				//创建Query对象
                    .list();						//返回结果集
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
        return list;
    }
    /**
     * 按hql删除信息
     * @param hql hql语句
     */
    public void deleteByHQL(String hql){
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            session.createQuery(hql)					//创建Query对象
                    .executeUpdate();					//更新
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
    }
    /**
     * 单值检索
     * @param hql hql语句
     * @param where 查询条件数组
     * @return Object
     */
    public Object uniqueResult(String hql,Object[] where){
        Object obj = null;
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            Query query = session.createQuery(hql);		//创建Query对象
            //如果where不为空，则对HQL语句进行动态赋值
            if(where != null && where.length > 0){
                for (int i = 0; i < where.length; i++) {
                    if(where[i] != null){
                        query = query.setParameter(i, where[i]);
                    }
                }
            }
            obj = query.uniqueResult();					//单值检索
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
        return obj;
    }
    /**
     * 分页查询
     * @param hql hql语句
     * @param offset 起始位置
     * @param length 偏移量
     * @param where 查询条件,Object数组类型
     * @return List集合
     */
    public List findPaging(String hql,int offset,int length,Object[] where){
        List list = null;
        try {
            session = HibernateSessionFactory.getSession();		//获取Session对象
            session.beginTransaction();					//开启事物
            Query query = session.createQuery(hql);		//创建Query对象
            //构建查询条件
            if(where != null && where.length > 0){
                for (int i = 0; i < where.length; i++) {
                    if(where[i] != null){
                        query = query.setParameter(i, where[i]);
                    }
                }
            }
            list = query.setFirstResult(offset)			//设置起始位置
                    .setMaxResults(length)			//偏移量
                    .list();						//获取结果集
            session.getTransaction().commit();			//提交事物
        } catch (Exception e) {
            e.printStackTrace();						//打印异常信息
            session.getTransaction().rollback();		//回滚事物
        }
        return list;
    }

}

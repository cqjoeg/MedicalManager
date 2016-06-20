package com.cq.dao.Impl;
import com.cq.dao.Interface.AnnounceDao;
import com.cq.persistence.TbAnnouncement;
import org.springframework.orm.hibernate4.HibernateTemplate;


import java.util.List;
/**
 * Created by admin on 2016/6/13.
 */
public class AnnounceDaoImpl implements AnnounceDao{
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 删除 Category
     * @return
     */
    public void deleteAnnounce(TbAnnouncement announcement){
        getHibernateTemplate().delete(announcement);
    }
    public  void saveAnnounce(TbAnnouncement announcement){
        getHibernateTemplate().save(announcement);
    }
    /**
     * @return
     */
    @Override
    public   void hot(TbAnnouncement tbAnnouncement){
        getHibernateTemplate().update(tbAnnouncement);
    }
    /**
     * 把 所有的hot 字段设置为0
     * @return
     */
    @Override
    public void update_column_hot(){
        List<TbAnnouncement> lit = (List<TbAnnouncement>) getHibernateTemplate().find("from TbAnnouncement where hot = 1");
        if (lit .size()== 0){
        }else {
            lit.get(0).setHot(0);
            getHibernateTemplate().update(lit.get(0));
        }

    }
    @Override
    public TbAnnouncement queryAnnouncementByID(int announceid) {
       List<TbAnnouncement> list = (List<TbAnnouncement>) getHibernateTemplate().find("from TbAnnouncement where id = ?",announceid);
        if(list.size() == 0) {		//判断是否查询到栏目
            return null;
        }else {
            return list.get(0);		//如果查询到，返回栏目
        }
    }

}

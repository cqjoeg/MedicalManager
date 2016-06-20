package com.cq.service.Impl;

import com.cq.dao.Interface.AnnounceDao;
import com.cq.hibernate.HibernateSessionFactory;
import com.cq.persistence.TbAnnouncement;
import com.cq.service.Interface.AnnounceService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by admin on 2016/6/13.
 */
public class AnnounceServiceImpl implements AnnounceService{
    private AnnounceDao announceDao;  //公告DAO 接口
    public AnnounceDao getAnnounceDao() {
        return announceDao;
    }
    public void setAnnounceDao(AnnounceDao announceDao) {
        this.announceDao = announceDao;
    }

    @Override
    public boolean deleteAnnounceByAnnounceId(int announceid) {
        TbAnnouncement announcement = announceDao.queryAnnouncementByID(announceid);
        if(announcement == null){
            return false;
        }else {
            announceDao.deleteAnnounce(announcement);
            return true;
        }
    }
    @Override
    public boolean addAnnounce(TbAnnouncement announcement) {
        announceDao.saveAnnounce(announcement);
        return false;
    }
    @Override
    public boolean updatehot(int announceid) {
        announceDao.update_column_hot();
        TbAnnouncement announcement = announceDao.queryAnnouncementByID(announceid);
        if(announcement == null){
            return false;
        }else {
            announcement.setHot(1);
            announceDao.hot(announcement);
            return true;
        }
    }

}

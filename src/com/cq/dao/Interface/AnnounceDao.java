package com.cq.dao.Interface;

import com.cq.persistence.TbAnnouncement;

/**
 * Created by admin on 2016/6/13.
 */
public interface AnnounceDao {
    public void deleteAnnounce(TbAnnouncement id);  //删除
    public void saveAnnounce(TbAnnouncement announcement);
    public void hot(TbAnnouncement announcement);
    public void update_column_hot();
    public TbAnnouncement queryAnnouncementByID(int announceid);  //根据 公告id 找到公告


}

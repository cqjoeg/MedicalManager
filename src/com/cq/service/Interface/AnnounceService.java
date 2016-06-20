package com.cq.service.Interface;

import com.cq.persistence.TbAnnouncement;

/**
 * Created by admin on 2016/6/13.
 */
public interface AnnounceService {
    public boolean deleteAnnounceByAnnounceId(int announceid);  //更具公告ID 删除公告
    public boolean addAnnounce(TbAnnouncement announcement);   //添加公告
    public boolean updatehot(int id);                           //更新 置顶公告
}

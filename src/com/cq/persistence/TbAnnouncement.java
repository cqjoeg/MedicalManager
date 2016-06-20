package com.cq.persistence;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by admin on 2016/6/13.
 */
@Entity
@Table(name = "tb_announcement", schema = "db_database25")
public class TbAnnouncement {
    private int id;
    private String announTitle;
    private String announContent;
    private String createTime;
    private int createUserId;
    private Integer hot;


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "announ_title")
    public String getAnnounTitle() {
        return announTitle;
    }

    public void setAnnounTitle(String announTitle) {
        this.announTitle = announTitle;
    }

    @Basic
    @Column(name = "announ_content")
    public String getAnnounContent() {
        return announContent;
    }

    public void setAnnounContent(String announContent) {
        this.announContent = announContent;
    }

    @Basic
    @Column(name = "createTime")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "createUserId")
    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "hot")
    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbAnnouncement that = (TbAnnouncement) o;

        if (id != that.id) return false;
        if (createUserId != that.createUserId) return false;
        if (announTitle != null ? !announTitle.equals(that.announTitle) : that.announTitle != null) return false;
        if (announContent != null ? !announContent.equals(that.announContent) : that.announContent != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (hot != null ? !hot.equals(that.hot) : that.hot != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (announTitle != null ? announTitle.hashCode() : 0);
        result = 31 * result + (announContent != null ? announContent.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + createUserId;
        result = 31 * result + (hot != null ? hot.hashCode() : 0);
        return result;
    }
}

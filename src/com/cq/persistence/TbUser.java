package com.cq.persistence;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 2016/5/28.
 */
@Entity
@Table(name = "tb_user", schema = "db_database25")
public class TbUser implements Serializable{
    private static final long serialVersionUID = 5810017231487090127L;
    private int id;
    private String username;
    private String password;
    private String createTime;
    private int state;    //  0 代表可用，1 代表不可用
    private String power;  //super 表示超级管理员，normal 表示普通管理员

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "power")
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUser tbUser = (TbUser) o;

        if (id != tbUser.id) return false;
        if (username != null ? !username.equals(tbUser.username) : tbUser.username != null) return false;
        if (password != null ? !password.equals(tbUser.password) : tbUser.password != null) return false;
        if (createTime != null ? !createTime.equals(tbUser.createTime) : tbUser.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }



}

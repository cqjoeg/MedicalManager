package com.cq.dao.Interface;

import com.cq.persistence.TbUser;

/**
 * Created by admin on 2016/6/13.
 */
public interface UserDao {
    public TbUser login(String userName, String password);
    public TbUser loadUser(int id);
    public int unlock(int id);
    public int lock(int id);
    public String addUser(TbUser tbUser);
}

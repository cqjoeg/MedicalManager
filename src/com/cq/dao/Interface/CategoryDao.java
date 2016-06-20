package com.cq.dao.Interface;

import com.cq.persistence.TbCategory;

/**
 * Created by admin on 2016/6/13.
 */
public interface CategoryDao {
    public String addCategory(TbCategory category);
    public TbCategory findById(int id );
    public boolean updateCategory(TbCategory tbCategory);
    public boolean deleteCategory(int id);
}

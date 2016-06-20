package com.cq.dao.Interface;

import com.cq.persistence.TbMedicine;

/**
 * Created by admin on 2016/6/13.
 */
public interface MedicalDao {
    public String addMedical(TbMedicine tbMedicine);
    public boolean deleteMedical(int id);
    public boolean updateMedical(TbMedicine tbMedicine);
    public TbMedicine findById(int id);
}

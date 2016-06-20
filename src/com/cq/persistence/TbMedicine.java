package com.cq.persistence;

import javax.persistence.*;

/**
 * Created by admin on 2016/6/11.
 */
@Entity
@Table(name = "tb_medicine", schema = "db_database25", catalog = "")
public class TbMedicine {
    private int id;
    private String medNo;
    private String name;
    private String factoryAdd;
    private String description;
    private double price;
    private Integer medCount;
    private Integer reqCount;
    private String photoPath;
    private Integer categoryId;

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
    @Column(name = "medNo")
    public String getMedNo() {
        return medNo;
    }

    public void setMedNo(String medNo) {
        this.medNo = medNo;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "factoryAdd")
    public String getFactoryAdd() {
        return factoryAdd;
    }

    public void setFactoryAdd(String factoryAdd) {
        this.factoryAdd = factoryAdd;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "medCount")
    public Integer getMedCount() {
        return medCount;
    }

    public void setMedCount(Integer medCount) {
        this.medCount = medCount;
    }

    @Basic
    @Column(name = "reqCount")
    public Integer getReqCount() {
        return reqCount;
    }

    public void setReqCount(Integer reqCount) {
        this.reqCount = reqCount;
    }

    @Basic
    @Column(name = "photoPath")
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Basic
    @Column(name = "categoryId")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbMedicine that = (TbMedicine) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (medNo != null ? !medNo.equals(that.medNo) : that.medNo != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (factoryAdd != null ? !factoryAdd.equals(that.factoryAdd) : that.factoryAdd != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (medCount != null ? !medCount.equals(that.medCount) : that.medCount != null) return false;
        if (reqCount != null ? !reqCount.equals(that.reqCount) : that.reqCount != null) return false;
        if (photoPath != null ? !photoPath.equals(that.photoPath) : that.photoPath != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (medNo != null ? medNo.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (factoryAdd != null ? factoryAdd.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (medCount != null ? medCount.hashCode() : 0);
        result = 31 * result + (reqCount != null ? reqCount.hashCode() : 0);
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }

    public void SET(Object o){
        TbMedicine that = (TbMedicine) o;
//          id = that.getId();
          medNo = that.getMedNo();
          name = that.getName();
          factoryAdd = that.getFactoryAdd();
          description = that.getDescription();
          price = that.getPrice();
          medCount = that.getMedCount();
          reqCount = that.getReqCount();
          photoPath = that.getPhotoPath();
          categoryId = that.getCategoryId();
    }

}

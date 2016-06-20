package com.cq.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2016/5/28.
 */
@Entity
@Table(name = "tb_selldetail", schema = "db_database25", catalog = "")
public class TbSelldetail implements Serializable{
    private static final long serialVersionUID = -607523072599700828L;
    private int id;
    private String sellName;
    private double sellPrice;
    private int sellCount;
    private Date sellTime;


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sellName")
    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    @Basic
    @Column(name = "sellPrice")
    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Basic
    @Column(name = "sellCount")
    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    @Basic
    @Column(name = "sellTime")
    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbSelldetail that = (TbSelldetail) o;

        if (id != that.id) return false;
        if (Double.compare(that.sellPrice, sellPrice) != 0) return false;
        if (sellCount != that.sellCount) return false;
        if (sellName != null ? !sellName.equals(that.sellName) : that.sellName != null) return false;
        if (sellTime != null ? !sellTime.equals(that.sellTime) : that.sellTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (sellName != null ? sellName.hashCode() : 0);
        temp = Double.doubleToLongBits(sellPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + sellCount;
        result = 31 * result + (sellTime != null ? sellTime.hashCode() : 0);
        return result;
    }
}

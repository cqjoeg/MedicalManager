package com.cq.persistence;

import javax.persistence.*;

/**
 * Created by admin on 2016/6/13.
 */
@Entity
@Table(name = "view_countsell", schema = "db_database25", catalog = "")
public class ViewCountsell {
    private int id;
    private String username;
    private int countsell;


    @Id
    @Column(name = "id")
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
    @Column(name = "countsell")
    public int getCountsell() {
        return countsell;
    }

    public void setCountsell(int countsell) {
        this.countsell = countsell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewCountsell that = (ViewCountsell) o;

        if (id != that.id) return false;
        if (countsell != that.countsell) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + countsell;
        return result;
    }
}

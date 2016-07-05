package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by Aska on 01.07.2016.
 */
@Entity
@Table(name = "staff")
public class StaffEntity {
    private int idStaff;

    @Id
    @Column(name = "staff_id")
    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffEntity that = (StaffEntity) o;

        if (idStaff != that.idStaff) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idStaff;
    }
}
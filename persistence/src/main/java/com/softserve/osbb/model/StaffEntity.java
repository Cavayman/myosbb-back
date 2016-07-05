package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "staff", schema = "public", catalog = "myosbb")
public class StaffEntity {
    private Integer staffId;
    private OsbbEntity osbbByOsbbId;
    private RoleEntity roleByRoleId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_id")
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffEntity that = (StaffEntity) o;

        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return staffId != null ? staffId.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public OsbbEntity getOsbbByOsbbId() {
        return osbbByOsbbId;
    }

    public void setOsbbByOsbbId(OsbbEntity osbbByOsbbId) {
        this.osbbByOsbbId = osbbByOsbbId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}

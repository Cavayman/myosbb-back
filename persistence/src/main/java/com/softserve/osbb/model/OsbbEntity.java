package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by Roman on 02.07.2016.
 */
@Entity
@Table(name = "osbb")
public class OsbbEntity {
    private int idOsbb;
    private String name;
    private String description;
    private byte idCreator;

    @Id
    @Column(name = "osbb_id")
    public int getIdOsbb() {
        return idOsbb;
    }

    public void setIdOsbb(int idOsbb) {
        this.idOsbb = idOsbb;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "creator_id")
    public byte getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(byte idCreator) {
        this.idCreator = idCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OsbbEntity that = (OsbbEntity) o;

        if (idOsbb != that.idOsbb) return false;
        if (idCreator != that.idCreator) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOsbb;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) idCreator;
        return result;
    }

    @Override
    public String toString() {
        return "OsbbEntity{" +
                "idOsbb=" + idOsbb +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", idCreator=" + idCreator +
                '}';
    }
}
package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "report")
public class Report {
    private Integer reportId;
    private String name;
    private String description;
    private Date datecreation;
    private String filepath;
    private Osbb osbbByOsbbId;

    public Report(){

    }

    public Report(String name, String description){
        this(name, description, new Date(),"no");
    }

    public Report(String name, String description, Date dateOfCreation, String filepath) {
        this.name = name;
        this.description = description;
        this.datecreation = dateOfCreation;
        this.filepath = filepath;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "datecreation")
    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    @Basic
    @Column(name = "filepath")
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report that = (Report) o;

        if (reportId != null ? !reportId.equals(that.reportId) : that.reportId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (datecreation != null ? !datecreation.equals(that.datecreation) : that.datecreation != null) return false;
        if (filepath != null ? !filepath.equals(that.filepath) : that.filepath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId != null ? reportId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (datecreation != null ? datecreation.hashCode() : 0);
        result = 31 * result + (filepath != null ? filepath.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbbByOsbbId() {
        return osbbByOsbbId;
    }

    public void setOsbbByOsbbId(Osbb osbbByOsbbId) {
        this.osbbByOsbbId = osbbByOsbbId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", datecreation=" + datecreation +
                ", filepath='" + filepath + '\'' +
                ", osbbByOsbbId=" + osbbByOsbbId +
                '}';
    }
}

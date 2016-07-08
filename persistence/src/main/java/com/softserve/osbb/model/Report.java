package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
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

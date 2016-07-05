package com.softserve.osbb.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@Entity
@Table(name = "report")
public class ReportEntity {
    private Long reportId;
    private String name;
    private String description;
    private Timestamp datecreation;
    private String filepath;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 400)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "datecreation", nullable = false)
    public Timestamp getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Timestamp datecreation) {
        this.datecreation = datecreation;
    }

    @Basic
    @Column(name = "filepath", nullable = true, length = 100)
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

        ReportEntity reportEntity = (ReportEntity) o;

        if (reportId != null ? !reportId.equals(reportEntity.reportId) : reportEntity.reportId != null) return false;
        if (name != null ? !name.equals(reportEntity.name) : reportEntity.name != null) return false;
        if (description != null ? !description.equals(reportEntity.description) : reportEntity.description != null) return false;
        if (datecreation != null ? !datecreation.equals(reportEntity.datecreation) : reportEntity.datecreation != null)
            return false;
        if (filepath != null ? !filepath.equals(reportEntity.filepath) : reportEntity.filepath != null) return false;

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
}

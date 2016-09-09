package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.utils.CustomLocalDateDeserializer;
import com.softserve.osbb.utils.CustomLocalDateSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Nazar Dovhyi
 */
@Entity
@Table(name = "report")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Report implements Serializable {
    public static final Report NO_REPORT = null;
    private Integer reportId;
    private String name;
    private String description;
    private LocalDate creationDate;
    private String filePath;
    private Osbb osbb;
    private User user;

    public Report() {
        //default constructor needed for Hibernate
    }

    public Report(String name, String description) {
        this(name, description, LocalDate.now(), "no");
    }

    public Report(String name, String description, LocalDate dateOfCreation, String filePath) {
        this.name = name;
        this.description = description;
        this.creationDate = dateOfCreation;
        this.filePath = filePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    public Integer getReportId() {
        return reportId == null ? new Integer(0) : reportId;
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
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "creationDate")
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "filePath")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
    @JsonIgnore
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbb() {
        return osbb;
    }

    public void setOsbb(Osbb osbb) {
        this.osbb = osbb;
    }

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

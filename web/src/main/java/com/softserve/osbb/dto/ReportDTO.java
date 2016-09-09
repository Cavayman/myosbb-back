package com.softserve.osbb.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.model.User;
import com.softserve.osbb.utils.CustomLocalDateDeserializer;
import com.softserve.osbb.utils.CustomLocalDateSerializer;

import java.time.LocalDate;

/**
 * Created by nazar.dovhyy on 28.08.2016.
 */
public class ReportDTO {

    private Integer reportId;
    private String name;
    private String description;
    private LocalDate creationDate;
    private String filePath;
    private Integer userId;

    public ReportDTO() {

    }

    public ReportDTO(ReportDTOBuilder reportDTOBuilder) {
        this.reportId = reportDTOBuilder.reportId;
        this.name = reportDTOBuilder.name;
        this.description = reportDTOBuilder.description;
        this.creationDate = reportDTOBuilder.creationDate;
        this.filePath = reportDTOBuilder.filePath;
    }


    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @JsonSerialize(using = CustomLocalDateSerializer.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static class ReportDTOBuilder {
        private Integer reportId;
        private String name;
        private String description;
        private LocalDate creationDate;
        private String filePath;
        private Integer userId;

        public ReportDTOBuilder setReportId(Integer reportId) {
            this.reportId = reportId;
            return this;
        }

        public ReportDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ReportDTOBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ReportDTOBuilder setCreationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public ReportDTOBuilder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public ReportDTOBuilder setUserId(User user) {
            if (user != null) {
                this.userId = user.getUserId();
            }
            return this;
        }

        public ReportDTO build() {
            return new ReportDTO(this);
        }
    }


    @Override
    public String toString() {
        return "ReportDTO{" +
                "reportId=" + reportId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

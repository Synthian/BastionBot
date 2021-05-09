package com.iancordle.bb.speedrun.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class Status {
    private String status;
    private String examiner;
    @JsonProperty("verify-date")
    private ZonedDateTime verifyDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }

    public ZonedDateTime getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(ZonedDateTime verifydate) {
        this.verifyDate = verifydate;
    }
}

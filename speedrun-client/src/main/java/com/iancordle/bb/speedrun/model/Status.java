package com.iancordle.bb.speedrun.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
    private String status;
    private String examiner;
    @JsonProperty("verify-date")
    private String verifydate;

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

    public String getVerifydate() {
        return verifydate;
    }

    public void setVerifydate(String verifydate) {
        this.verifydate = verifydate;
    }
}

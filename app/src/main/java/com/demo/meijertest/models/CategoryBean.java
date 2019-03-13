package com.demo.meijertest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryBean {
    @Expose
    @SerializedName("segmentName")
    private String segmentName;
    @Expose
    @SerializedName("segmentID")
    private String segmentID;

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(String segmentID) {
        this.segmentID = segmentID;
    }
}

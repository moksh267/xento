package com.xento.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prashant.mudgal on 19-12-2017.
 */

public class RootObject {
    @SerializedName("StatusMsg")
    @Expose
    private String statusMsg;
    @SerializedName("Results")
    @Expose
    private JsonObject countries;
    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public JsonObject getResults() {
        return countries;
    }

    public void setResults(JsonObject countries) {
        this.countries = countries;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "RootObject{" +
                "statusMsg='" + statusMsg + '\'' +
                ", countries=" + countries +
                ", statusCode=" + statusCode +
                '}';
    }
}

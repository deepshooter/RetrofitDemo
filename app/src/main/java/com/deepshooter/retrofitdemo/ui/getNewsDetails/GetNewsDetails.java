
package com.deepshooter.retrofitdemo.ui.getNewsDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetNewsDetails {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("base_url")
    @Expose
    private String baseUrl;
    @SerializedName("file_relative_url")
    @Expose
    private String fileRelativeUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetNewsDetails() {
    }

    /**
     * 
     * @param baseUrl
     * @param response
     * @param fileRelativeUrl
     * @param error
     */
    public GetNewsDetails(Integer error, List<Response> response, String baseUrl, String fileRelativeUrl) {
        super();
        this.error = error;
        this.response = response;
        this.baseUrl = baseUrl;
        this.fileRelativeUrl = fileRelativeUrl;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getFileRelativeUrl() {
        return fileRelativeUrl;
    }

    public void setFileRelativeUrl(String fileRelativeUrl) {
        this.fileRelativeUrl = fileRelativeUrl;
    }

}


package com.deepshooter.retrofitdemo.ui.getNewsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNewsList {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("response")
    @Expose
    private Response response;
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
    public GetNewsList() {
    }

    /**
     * 
     * @param baseUrl
     * @param response
     * @param fileRelativeUrl
     * @param error
     */
    public GetNewsList(Integer error, Response response, String baseUrl, String fileRelativeUrl) {
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

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
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

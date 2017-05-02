
package com.deepshooter.retrofitdemo.ui.getNewsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("short_desc")
    @Expose
    private String shortDesc;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("poster")
    @Expose
    private Object poster;
    @SerializedName("department_id")
    @Expose
    private Integer departmentId;

    @SerializedName("document")
    @Expose
    private String document;

    @SerializedName("publish_date")
    @Expose
    private String publishDate;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param id
     * @param description
     * @param poster
     * @param caption
     * @param departmentId
     * @param shortDesc
     */
    public Datum(Integer id, String caption, String shortDesc, String description, Object poster, Integer departmentId,String document,String publishDate) {
        super();
        this.id = id;
        this.caption = caption;
        this.shortDesc = shortDesc;
        this.description = description;
        this.poster = poster;
        this.departmentId = departmentId;
        this.document = document;
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getPoster() {
        return poster;
    }

    public void setPoster(Object poster) {
        this.poster = poster;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

}

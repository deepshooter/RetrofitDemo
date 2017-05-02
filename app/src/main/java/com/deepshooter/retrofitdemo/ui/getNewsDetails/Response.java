
package com.deepshooter.retrofitdemo.ui.getNewsDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("caption_ka")
    @Expose
    private String captionKa;
    @SerializedName("short_desc")
    @Expose
    private String shortDesc;
    @SerializedName("short_desc_ka")
    @Expose
    private String shortDescKa;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_ka")
    @Expose
    private String descriptionKa;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param updatedAt
     * @param captionKa
     * @param id
     * @param status
     * @param createdAt
     * @param description
     * @param deletedAt
     * @param poster
     * @param shortDescKa
     * @param caption
     * @param shortDesc
     * @param descriptionKa
     */
    public Response(Integer id, String caption, String captionKa, String shortDesc, String shortDescKa, String description, String descriptionKa, String poster, Integer status, String createdAt, String updatedAt, Object deletedAt) {
        super();
        this.id = id;
        this.caption = caption;
        this.captionKa = captionKa;
        this.shortDesc = shortDesc;
        this.shortDescKa = shortDescKa;
        this.description = description;
        this.descriptionKa = descriptionKa;
        this.poster = poster;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
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

    public String getCaptionKa() {
        return captionKa;
    }

    public void setCaptionKa(String captionKa) {
        this.captionKa = captionKa;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortDescKa() {
        return shortDescKa;
    }

    public void setShortDescKa(String shortDescKa) {
        this.shortDescKa = shortDescKa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionKa() {
        return descriptionKa;
    }

    public void setDescriptionKa(String descriptionKa) {
        this.descriptionKa = descriptionKa;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}

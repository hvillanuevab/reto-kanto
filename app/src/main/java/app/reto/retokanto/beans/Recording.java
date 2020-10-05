package app.reto.retokanto.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recording {

    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("record_video")
    @Expose
    private String recordVideo;
    @SerializedName("preview_img")
    @Expose
    private String previewImg;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecordVideo() {
        return recordVideo;
    }

    public void setRecordVideo(String recordVideo) {
        this.recordVideo = recordVideo;
    }

    public String getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
    }

}
package app.reto.retokanto.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class User extends SugarRecord {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("biography")
    @Expose
    private String biography;

    @SerializedName("followers")
    @Expose
    private Integer followers;

    @SerializedName("followed")
    @Expose
    private Integer followed;

    @SerializedName("views")
    @Expose
    private Integer views;

    @SerializedName("profilePicture")
    @Expose
    private String profilePicture;

    public User() {
    }

    public User(String name, String userName, String biography, Integer followers, Integer followed, Integer views, String profilePicture) {
        this.name = name;
        this.userName = userName;
        this.biography = biography;
        this.followers = followers;
        this.followed = followed;
        this.views = views;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowed() {
        return followed;
    }

    public void setFollowed(Integer followed) {
        this.followed = followed;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

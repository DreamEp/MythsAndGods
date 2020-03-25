package fr.esilv.mythsandgods.ViewPager;


import java.io.Serializable;


public class DivinityItem implements Serializable {
    private String name;
    private String title;
    private String picture;
    private String website;
    private boolean favorite;

    public DivinityItem(String name, String titre, String picture, String website, boolean favorite) {
        this.name = name;
        this.title = titre;
        this.picture = picture;
        this.website = website;
        this.favorite = favorite;
    }

    public DivinityItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

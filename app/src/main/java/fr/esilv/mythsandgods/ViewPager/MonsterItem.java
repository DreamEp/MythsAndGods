package fr.esilv.mythsandgods.ViewPager;

import java.io.Serializable;

public class MonsterItem implements Serializable {
    private String name;
    private String title;
    private String website;
    private String picture;

    public MonsterItem(String name, String title, String picture, String website) {
        this.name = name;
        this.title = title;
        this.picture = picture;
        this.website = website;
    }

    public MonsterItem() {
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
}

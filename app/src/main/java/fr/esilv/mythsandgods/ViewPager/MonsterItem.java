package fr.esilv.mythsandgods.ViewPager;

import java.io.Serializable;

public class MonsterItem implements Serializable {
    private String name;
    private String title;
    private String presentation;
    private String picture;

    public MonsterItem(String name, String title, String picture, String presentation) {
        this.name = name;
        this.title = title;
        this.picture = picture;
        this.presentation = presentation;
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

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
}

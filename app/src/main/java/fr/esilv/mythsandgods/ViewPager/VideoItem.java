package fr.esilv.mythsandgods.ViewPager;

import java.io.Serializable;


public class VideoItem implements Serializable {
    //Déclarer éléments de l'item
    private String name;
    private String description;
    private String url;

    public VideoItem(String url, String name, String description)
    {
        this.url = url;
        this.name = name;
        this.description = description;
    }

    public VideoItem() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

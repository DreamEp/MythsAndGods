package fr.esilv.mythsandgods.ViewPager;

import java.io.Serializable;


public class VideoItem implements Serializable {
    //Déclarer éléments de l'item
    private String name;
    private int id;
    private String description;
    private String url;

    public VideoItem(String url, String name, int id)
    {
        this.url = url;
        this.name = name;
        this.id = id;
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

    public String getId() {
        return name;
    }

    public void setId(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package fr.esilv.mythsandgods.ViewPager;

import java.io.Serializable;
import java.net.URL;

public class MonsterItem implements Serializable {
    private String name;
    private String title;
    private URL presentation;

    public MonsterItem(String name, String title, URL presentation) {
        this.name = name;
        this.title = title;
        this.presentation = presentation;
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

    public URL getPresentation() {
        return presentation;
    }

    public void setPresentation(URL presentation) {
        this.presentation = presentation;
    }
}

package fr.esilv.mythsandgods.ViewPager;

import java.io.Serializable;

public class MonsterItem implements Serializable {
    private String name;
    private String title;
    private String presentation;

    public MonsterItem(String name, String title, String presentation) {
        this.name = name;
        this.title = title;
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

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
}

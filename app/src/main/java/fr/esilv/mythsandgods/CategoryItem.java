package fr.esilv.mythsandgods;


import java.io.Serializable;
import java.net.URL;

public class CategoryItem implements Serializable {

    private String name;
    private URL picture;
    private String summary;
    private URL website;
    private DivinityItem divinity_list[];
    private MonsterItem monster_list[];

    public CategoryItem(String name, URL picture, String summary, URL website, DivinityItem[] divinity_list, MonsterItem[] monster_list) {
        this.name = name;
        this.picture = picture;
        this.summary = summary;
        this.website = website;
        this.divinity_list = divinity_list;
        this.monster_list = monster_list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public URL getWebsite() {
        return website;
    }

    public void setWebsite(URL website) {
        this.website = website;
    }

    public DivinityItem[] getDivinity_list() {
        return divinity_list;
    }

    public void setDivinity_list(DivinityItem[] divinity_list) {
        this.divinity_list = divinity_list;
    }

    public MonsterItem[] getMonster_list() {
        return monster_list;
    }

    public void setMonster_list(MonsterItem[] monster_list) {
        this.monster_list = monster_list;
    }
}

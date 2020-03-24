package fr.esilv.mythsandgods.Category;


import java.io.Serializable;
import java.util.ArrayList;

import fr.esilv.mythsandgods.ViewPager.DivinityItem;
import fr.esilv.mythsandgods.ViewPager.MonsterItem;

public class CategoryItem implements Serializable {

    private int id;
    private String category_name;
    private String category_picture;
    private String category_summary;
    private String website;
    private ArrayList<DivinityItem> divinity_list;
    private ArrayList<MonsterItem> monster_list;

    public CategoryItem(String category_name, String category_picture, String category_summary, String website, ArrayList<DivinityItem> divinity_list, ArrayList<MonsterItem> monster_list, int id) {
        this.id =id;
        this.category_name = category_name;
        this.category_picture = category_picture;
        this.category_summary = category_summary;
        this.website = website;
        this.divinity_list = divinity_list;
        this.monster_list = monster_list;
    }

    public CategoryItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_picture() {
        return category_picture;
    }

    public void setCategory_picture(String category_picture) {
        this.category_picture = category_picture;
    }

    public String getCategory_summary() {
        return category_summary;
    }

    public void setCategory_summary(String category_summary) {
        this.category_summary = category_summary;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ArrayList<DivinityItem> getDivinity_list() {
        return divinity_list;
    }

    public void setDivinity_list(ArrayList<DivinityItem> divinity_list) {
        this.divinity_list = divinity_list;
    }

    public ArrayList<MonsterItem> getMonster_list() {
        return monster_list;
    }

    public void setMonster_list(ArrayList<MonsterItem> monster_list) {
        this.monster_list = monster_list;
    }
}

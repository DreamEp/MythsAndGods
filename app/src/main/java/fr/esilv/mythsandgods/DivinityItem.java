package fr.esilv.mythsandgods;

import java.io.Serializable;
import java.net.URL;

public class DivinityItem implements Serializable {
    private String name;
    private String titre;
    private URL picture;
    private URL presentation;
    private boolean favorite;

    public DivinityItem(String name, String titre, URL picture, URL presentation, boolean favorite) {
        this.name = name;
        this.titre = titre;
        this.picture = picture;
        this.presentation = presentation;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public URL getPresentation() {
        return presentation;
    }

    public void setPresentation(URL presentation) {
        this.presentation = presentation;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

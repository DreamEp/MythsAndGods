package fr.esilv.mythsandgods;

import android.widget.RelativeLayout;

public class CategoryItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public CategoryItem(int imageRessource, String text1, String text2){
        mImageResource = imageRessource;
        mText1 = text1;
        mText2 = text2;
    }

    public void changeText1(String text){
        mText1 = text;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }

}

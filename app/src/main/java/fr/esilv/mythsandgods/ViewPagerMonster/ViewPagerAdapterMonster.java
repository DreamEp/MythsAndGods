package fr.esilv.mythsandgods.ViewPagerMonster;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fr.esilv.mythsandgods.ViewPager.SummaryCategoryFragment;
import fr.esilv.mythsandgods.ViewPager.WebViewFragment;


public class ViewPagerAdapterMonster extends FragmentStatePagerAdapter {
    private int tabCount;
    private String website;
    private String title;
    private String[] tabTitles = new String[] {"Site", "Résumé"};

    public ViewPagerAdapterMonster(FragmentManager fm, int tabCount, String website, String title) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.website=website;
        this.title = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                WebViewFragment tab1 = new WebViewFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("key_website", website);
                tab1.setArguments(bundle1);
                return tab1;
            case 1:
                SummaryCategoryFragment tab2 = new SummaryCategoryFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("key_title", title);
                tab2.setArguments(bundle2);
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

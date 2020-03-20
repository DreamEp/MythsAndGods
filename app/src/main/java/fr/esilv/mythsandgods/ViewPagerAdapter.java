package fr.esilv.mythsandgods;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    private String[] tabTitles = new String[] {"Favoris", "WebView", "Catégories"};

    public ViewPagerAdapter(FragmentManager fm, int tabCount /*,String category*/) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
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
                FavoriteFragment tab1 = new FavoriteFragment();
                return tab1;
            case 1:
                WebViewFragment tab2 = new WebViewFragment();
                return tab2;
            case 2:
                CategoryFragment tab3 = new CategoryFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

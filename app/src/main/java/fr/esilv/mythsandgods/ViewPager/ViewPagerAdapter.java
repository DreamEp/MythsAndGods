package fr.esilv.mythsandgods.ViewPager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fr.esilv.mythsandgods.Category.FavoriteFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int id;
    private int tabCount;
    private String website;
    private String summary;

    private String[] tabTitles = new String[] {"Résumé", "Site", "Divinités", "Monstres", "Vidéos"};

    public ViewPagerAdapter(FragmentManager fm, int tabCount ,int id, String website, String summary) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.id=id;
        this.website=website;
        this.summary=summary;

        //A décommenter
        //this.videoListString = videoListString;
        //videoList = videoListString.split(";");
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
                SummaryCategoryFragment tab1 = new SummaryCategoryFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("key_summary", summary);
                tab1.setArguments(bundle1);
                return tab1;
            case 1:
                WebViewFragment tab2 = new WebViewFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("key_website", website);
                tab2.setArguments(bundle2);
                return tab2;
            case 2:
                DivinityFragment tab3 = new DivinityFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("key_id",id);
                tab3.setArguments(bundle3);
                return tab3;
            case 3:
                MonsterFragment tab4 = new MonsterFragment();
                Bundle bundle4 = new Bundle();
                bundle4.putInt("key_id",id);
                tab4.setArguments(bundle4);
                return tab4;
            case 4:
                VideoFragment tab5 = new VideoFragment();
                Bundle bundle5 = new Bundle();
                bundle5.putInt("key_id", id);
                return tab5;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

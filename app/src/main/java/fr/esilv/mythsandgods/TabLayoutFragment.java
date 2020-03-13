package fr.esilv.mythsandgods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TabLayoutFragment extends Fragment {

    private TabLayoutAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swipe_view,container,false);

        mSectionsPagerAdapter = new TabLayoutAdapter(getFragmentManager());

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(mViewPager);


        return rootView;
    }

}

package fr.esilv.mythsandgods.ViewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;


import fr.esilv.mythsandgods.Category.CategoryActivity;
import fr.esilv.mythsandgods.R;

public class ViewPagerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        int id = getIntent().getIntExtra("key_id",0);
        String category_name = getIntent().getStringExtra("key_category_name");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(category_name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_home);
        //Change Color
        /*ColorDrawable color1 = new ColorDrawable(Color.parseColor("#9FCAEE"));
        if(id == 1) getSupportActionBar().setBackgroundDrawable(color1);
        ColorDrawable color2 = new ColorDrawable(Color.parseColor("#9c732f"));
        if(id == 2) getSupportActionBar().setBackgroundDrawable(color2);*/



        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Change color
        //if(id == 1) tabLayout.setBackgroundColor(Color.parseColor("#9FCAEE"));
        //if(id == 2) tabLayout.setBackgroundColor(Color.parseColor("#9c732f"));

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        String website = getIntent().getStringExtra("key_website");
        String summary = getIntent().getStringExtra("key_summary");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), id, website, summary);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

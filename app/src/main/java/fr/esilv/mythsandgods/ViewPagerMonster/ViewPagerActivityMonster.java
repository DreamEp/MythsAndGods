package fr.esilv.mythsandgods.ViewPagerMonster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import fr.esilv.mythsandgods.Category.CategoryActivity;
import fr.esilv.mythsandgods.R;

public class ViewPagerActivityMonster extends AppCompatActivity {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        String monster_name = getIntent().getStringExtra("key_name");
        String monster_title = getIntent().getStringExtra("key_title");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(monster_name +" : "+monster_title);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_home);


        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        String website = getIntent().getStringExtra("key_website");
        ViewPagerAdapterMonster adapter = new ViewPagerAdapterMonster(getSupportFragmentManager(), tabLayout.getTabCount(), website, monster_title);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivityMonster.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }


    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }


    public void onTabUnselected(TabLayout.Tab tab) {

    }


    public void onTabReselected(TabLayout.Tab tab) {

    }

}

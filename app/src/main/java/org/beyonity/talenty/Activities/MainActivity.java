package org.beyonity.talenty.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.beyonity.talenty.CustomPages.CustomViewPager;
import org.beyonity.talenty.Fragments.fragment_videoScreen;
import org.beyonity.talenty.Fragments.fragment_home;
import org.beyonity.talenty.Fragments.fragment_notification;
import org.beyonity.talenty.Fragments.fragment_profile;
import org.beyonity.talenty.Fragments.fragment_search;
import org.beyonity.talenty.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[][] states = new int[][]{
            new int[]{-android.R.attr.state_checked},  // unchecked
            new int[]{android.R.attr.state_checked}};   // checked
    int[] Homecolors = new int[]{
            Color.parseColor("#EDE8E8"),
            Color.parseColor("#ffffff")
    };
    int[] othercolors = new int[]{
            Color.parseColor("#969696"),
            Color.parseColor("#000000")
    };

    ColorStateList HomenavigationViewColorStateList = new ColorStateList(states, Homecolors);
    ColorStateList othernavigationViewColorStateList = new ColorStateList(states, othercolors);
            CustomViewPager vg;
    BottomNavigationView bottomNavigationView;
    fragmentspageadapter pageAdapter;

    //fragments
    fragment_home home;
    fragment_search search;
    fragment_videoScreen video;
    fragment_notification notification;
    fragment_profile profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        home = new fragment_home();
        search = new fragment_search();
        video = new fragment_videoScreen();
        notification = new fragment_notification();
        profile = new fragment_profile();


        vg = (CustomViewPager) findViewById(R.id.vg);
        vg.disableScroll(true);
        pageAdapter = new fragmentspageadapter(getSupportFragmentManager());
        pageAdapter.addFragment(home);
        pageAdapter.addFragment(search);
        pageAdapter.addFragment(video);
        pageAdapter.addFragment(notification);
        pageAdapter.addFragment(profile);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        vg.setAdapter(pageAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home: {
                        vg.setCurrentItem(0);
                        break;
                    }
                    case R.id.search: {
                        vg.setCurrentItem(1);
                        break;
                    }
                    case R.id.add: {
                        vg.setCurrentItem(2);
                        break;
                    }

                    case R.id.inbox: {
                        vg.setCurrentItem(3);
                        break;
                    }

                    case R.id.profile: {
                        vg.setCurrentItem(4);
                        break;
                    }

                }

                if(!(item.getItemId() ==R.id.home)){
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    bottomNavigationView.setBackgroundColor(Color.WHITE);
                    bottomNavigationView.setItemIconTintList(othernavigationViewColorStateList);
                    bottomNavigationView.setItemTextColor(othernavigationViewColorStateList);
                }else {
                    bottomNavigationView.setBackgroundColor(Color.TRANSPARENT);
                    bottomNavigationView.setItemIconTintList(HomenavigationViewColorStateList);
                    bottomNavigationView.setItemTextColor(HomenavigationViewColorStateList);
                    getWindow().getDecorView().setSystemUiVisibility(0);
                }
                return true;
            }
        });


    }


    public class fragmentspageadapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments = new ArrayList<>();

        public fragmentspageadapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public void addFragment(Fragment fragment){
            fragments.add(fragment);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}

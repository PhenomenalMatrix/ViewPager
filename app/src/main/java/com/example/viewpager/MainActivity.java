package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.viewpager.adapters.FragmentAdapter;
import com.example.viewpager.ui.collection.CollectionFragment;
import com.example.viewpager.ui.favorite.FavoriteFragment;
import com.example.viewpager.ui.list.ListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = new ArrayList<>();
        fillFragment();

        bottomNavigationView = findViewById(R.id.bottom_navegtion);
        viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentAdapter(fragmentList, getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_list){
                    viewPager.setCurrentItem(0);
                } else if(item.getItemId() == R.id.menu_fav){
                    viewPager.setCurrentItem(1);
                } else if(item.getItemId() == R.id.menu_collection) {
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }
    private void fillFragment(){
        fragmentList.add(new ListFragment());
        fragmentList.add(new FavoriteFragment());
        fragmentList.add(new CollectionFragment());
    }
}
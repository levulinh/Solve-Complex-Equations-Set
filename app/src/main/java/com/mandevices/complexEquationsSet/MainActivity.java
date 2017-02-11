package com.mandevices.complexEquationsSet;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems;

    private Toolbar toolbar;
    private AHBottomNavigation bottomNavigation;
    private AHBottomNavigationViewPager viewPager;
    private FloatingActionButton fab;
    private MyFragment currentFragment;
    private MyViewPagerAdapter adapter;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.solve_three_var_equation_set);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        initUI();
    }

    private void initUI() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        fab = (FloatingActionButton) findViewById(R.id.floating_action_button);

        /**
         * Thiết lập các item trong {@param bottomNavigation}
         */

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_home, R.color.color_tab_2);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_three, R.color.color_tab_1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.item_4_vars, R.drawable.ic_four, R.color.color_tab_4);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_settings, R.color.color_tab_3);

        bottomNavigationItems = new ArrayList<>();
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);

        bottomNavigation.addItems(bottomNavigationItems);

        bottomNavigation.manageFloatingActionButtonBehavior(fab);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setColored(true);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (currentFragment == null) {
                    currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {

                }

                if (currentFragment != null) {
                    //currentFragment.willBeHidden();
                }

                if (position == 0) {
                    getSupportActionBar().setTitle(R.string.solve_2_equa_set);
                } else if (position == 1) {
                    toolbar.setTitle(R.string.solve_3_equa_set);
                    //bottomNavigation.setNotification("", 1);
                } else if (position == 2) {
                    toolbar.setTitle(R.string.solve_4_equa_set);
                    //bottomNavigation.setNotification("", 1);
                } else
                    toolbar.setTitle(R.string.info);

                viewPager.setCurrentItem(position, false);
                currentFragment = adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();

                return true;
            }
        });
        viewPager.setOffscreenPageLimit(4);
        adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position, false);
                if (position == 0) {
                    getSupportActionBar().setTitle(R.string.solve_2_equa_set);
                } else if (position == 1) {
                    toolbar.setTitle(R.string.solve_3_equa_set);
                    //bottomNavigation.setNotification("", 1);
                } else if (position == 2) {
                    toolbar.setTitle(R.string.solve_4_equa_set);
                    //bottomNavigation.setNotification("", 1);
                } else
                    toolbar.setTitle(R.string.info);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        currentFragment = adapter.getCurrentFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.mnu_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater = MainActivity.this.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.dialog_layout, null));
            builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();

            TextView txt_title = (TextView) dialog.findViewById(R.id.txt_title);
            TextView txt_content = (TextView) dialog.findViewById(R.id.txt_content);

            txt_title.setText("Thông tin tác giả");
            txt_title.setBackgroundColor(getResources().getColor(R.color.buttonNormal));
            txt_content.setText("Tác giả: Lê Vũ Linh\n" +
                    "CTTT Điện-Điện Tử K59\n" +
                    "ĐH Bách Khoa HN");
        }
        return true;
    }
}

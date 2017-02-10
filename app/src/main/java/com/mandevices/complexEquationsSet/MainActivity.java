package com.mandevices.complexEquationsSet;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

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
        toolbar.setTitle("Giải hệ phức 3 ẩn");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);

//        Slide slideTransition = new Slide();
//        slideTransition.setSlideEdge(Gravity.LEFT);
//        slideTransition.setDuration(500);
//        getWindow().setEnterTransition(slideTransition);

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
                    currentFragment.willBeHidden();
                }

                if (position == 0) {
                    getSupportActionBar().setTitle("Giải hệ phức 2 ẩn");
                } else if (position == 1) {
                    toolbar.setTitle("Giải hệ phức 3 ẩn");
                    bottomNavigation.setNotification("", 1);
                } else if (position == 2) {
                    toolbar.setTitle("Giải hệ phức 4 ẩn");
                    bottomNavigation.setNotification("", 1);
                } else
                    toolbar.setTitle("Thông tin");

                viewPager.setCurrentItem(position, false);
                currentFragment = adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();

                return true;
            }
        });
        viewPager.setOffscreenPageLimit(4);
        adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        AHNotification notification = new AHNotification.Builder()
                .setText(":)")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_back))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_text))
                .build();
        //bottomNavigation.setNotification(notification, 1);
//        Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
//                Snackbar.LENGTH_SHORT).show();

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
            builder.setTitle("Thông tin tác giả")
                    .setMessage("Tác giả: Lê Vũ Linh\n" +
                            "CTTT Điện-Điện Tử K59\n" +
                            "ĐH Bách Khoa HN")
                    .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }
}

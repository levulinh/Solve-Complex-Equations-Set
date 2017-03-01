package com.mandevices.complexEquationsSet;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems;
    private Toolbar toolbar;
    private AHBottomNavigation bottomNavigation;
    private AHBottomNavigationViewPager viewPager;
    private FloatingActionButton floatingActionButton;
    private MyFragment currentFragment;
    private MyViewPagerAdapter adapter;

    private boolean colored = true;
    private String language;
    private String lastLanguage;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.solve_three_var_equation_set);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initUI();
    }

    private void restartActivity() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void initUI() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);

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

        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.setColored(colored);

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

        switch (item.getItemId()){
            case R.id.mnu_about:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.dialog_layout, null));
                builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();

                TextView txt_title = (TextView) dialog.findViewById(R.id.txt_title);
                TextView txt_content = (TextView) dialog.findViewById(R.id.txt_content);

                txt_title.setText(R.string.author_info);
                txt_title.setBackgroundColor(getResources().getColor(R.color.buttonNormal));
                txt_content.setText(getString(R.string.author_info_content));
                break;
            case R.id.mnu_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.mnu_convert_pi_tool:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater2 = MainActivity.this.getLayoutInflater();
                builder2.setView(inflater2.inflate(R.layout.dialog_tool_pi2float, null));
                builder2.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                final AlertDialog dialog2 = builder2.create();
                dialog2.show();

                final TextInputLayout layout_number = (TextInputLayout) dialog2.findViewById(R.id.layout_number);
                final EditText edt_number = (EditText) dialog2.findViewById(R.id.edt_number);
                final Button btn_convert = (Button) dialog2.findViewById(R.id.btn_convert);
                final TextView txt_convert_result = (TextView) dialog2.findViewById(R.id.txt_convert_result);

                btn_convert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            DecimalFormat df = new DecimalFormat(".####");
                            txt_convert_result.setText(df.format(Float.parseFloat(edt_number.getText().toString())* 3.1415926) + "");
                            btn_convert.setText(R.string.hold_to_copy);}
                        catch (Exception ex){
                            layout_number.setError(getString(R.string.enter_number_error));
                            edt_number.requestFocus();
                        }
                    }
                });

                btn_convert.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Copied Text", txt_convert_result.getText().toString());
                        clipboard.setPrimaryClip(clip);
                        View view1 = findViewById(R.id.coordinator);
                        Snackbar.make(view1, R.string.copied_to_clipboard, BaseTransientBottomBar.LENGTH_SHORT).show();
                        dialog2.dismiss();
                        return true;
                    }
                });
                break;
            /*case R.id.mnu_convert_polar_normal:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater3 = MainActivity.this.getLayoutInflater();
                builder3.setView(inflater3.inflate(R.layout.dialog_convert_forms, null));
                builder3.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                final AlertDialog dialog3 = builder3.create();
                dialog3.show();

                TextInputLayout layout_polar = (TextInputLayout) dialog3.findViewById(R.id.layout_polar);
                TextInputLayout layout_normal = (TextInputLayout) dialog3.findViewById(R.id.layout_normal);
                final EditText edt_polar = (EditText) dialog3.findViewById(R.id.edt_polar);
                final EditText edt_normal = (EditText) dialog3.findViewById(R.id.edt_normal);

                edt_normal.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try{
//                            ComplexNumber.parseCplx(charSequence.toString());
                            edt_polar.setText(ComplexNumber.parseCplx(charSequence.toString()).toPolar());
                        }catch (Exception ex){
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                edt_polar.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try{
                            edt_normal.setText(ComplexNumber.parseCplx(charSequence.toString()).toString());
                        }catch (Exception ex){
                            //do nothing
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                break;*/
        }
          return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        getSharedPref();
    }

    public void getSharedPref() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        language = preferences.getString("pref_choose_language", "no");
        lastLanguage = preferences.getString("pref_last_language", "no");
        colored = preferences.getBoolean("pref_show_colors", true);
        bottomNavigation.setColored(colored);


        Log.w("LOCALE", colored + " " + language);
        if (!language.equals(lastLanguage)) {
            Locale myLocale = new Locale(language);
            Locale.setDefault(myLocale);
            android.content.res.Configuration config = new android.content.res.Configuration();
            config.locale = myLocale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("pref_last_language", language);
            editor.commit();

            restartActivity();
        }
    }
}

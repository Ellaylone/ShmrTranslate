package com.example.ellaylone.shmrtranslate;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создаем кастомный тулбар
        Toolbar customToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        customToolbar.setTitle("");
        setSupportActionBar(customToolbar);

        // создаем спиннер
        populateSpinner(R.id.origin_language);
        populateSpinner(R.id.translation_language);

        setupTabLayout();
    }

    private void setupTabLayout () {
        // кастомный ViewPager чтобы избавиться от перелистывания фрагментов свайпом
        SwipeViewPager swipeViewPager = (SwipeViewPager) findViewById(R.id.viewpager);

        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        swipeViewPager.setAdapter(adapter);
        swipeViewPager.setEnabled(false);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(swipeViewPager);

        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (isOpen) {
                    tabLayout.setVisibility(isOpen ? View.GONE : View.VISIBLE);
                } else {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    tabLayout.setVisibility(View.VISIBLE);
                                }
                            }, 100);
                }
            }
        });
    }

    private void populateSpinner (int spinnerResourceId) {
        Spinner spinner = (Spinner) findViewById(spinnerResourceId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

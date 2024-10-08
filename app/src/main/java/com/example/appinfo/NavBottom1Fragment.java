package com.example.appinfo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;


public class NavBottom1Fragment extends Fragment {

    FrameLayout frameLayout;
    TabLayout tabLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav_bottom1, container, false);

        // Tìm view FrameLayout và TabLayout
        frameLayout = view.findViewById(R.id.frameLayout_tab);
        tabLayout = view.findViewById(R.id.tab_layout);

        replaceFragment(new Tab1Fragment());

        // Thêm sự kiện click cho từng tab trong TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new Tab1Fragment();  // Tab 1
                        break;
                    case 1:
                        fragment = new Tag2Fragment();  // Tab 2
                        break;
                }
                if (fragment != null) {
                    replaceFragment(fragment);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Xử lý khi tab không được chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Xử lý khi tab được chọn lại
            }


        });
        setTabDividers();
        return view;

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout_tab, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }
    // Tạo đường kẻ trong tab view
    private void setTabDividers() {
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.WHITE);
            drawable.setSize(30, 1);

            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }
}
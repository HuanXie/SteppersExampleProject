package com.example.huaxie.steppersexampleproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huaxie on 10/23/2017.
 */

public class BaseFragment extends Fragment {
    public SwipeDisabledViewPager viewPager;
    public TabLayout tabLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_base, container, false);
        List<String> numberList = Arrays.asList("Page 1", "Page 2", "Page 3" );
        this.viewPager = view.findViewById(R.id.viewpager);
        setViewPagerAdapter(numberList);
        this.tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayoutTabSelectedListener());
        setTabView();
        disableTabsClicking();
        return view;
    }

    private void setViewPagerAdapter(final List<String> numberList) {
        viewPager.setAdapter(new TabPageAbstractAdapter(getChildFragmentManager(),getContext()) {
            @Override
            public Fragment getItem(int position) {
                return SimpleFragment.newInstance(position, numberList.size());
            }

            @Override
            public int getCount() {
                return numberList.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPagerChangeListener());
        viewPager.setPagingEnabled(false);
    }

    private class TabLayoutTabSelectedListener implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).setLastStep(position == tabLayout.getTabCount() - 1);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            if (viewPager.getCurrentItem() > tab.getPosition()) {
                changeTabItemLayout(tab, R.drawable.complete_tab, "\u2713",R.color.black);
            }else {
                changeTabItemLayout(tab,R.drawable.checked_circle_selector,String.valueOf(tab.getPosition() + 1),R.color.grey);
            }
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    }

    private void changeTabItemLayout(TabLayout.Tab tab, int drawableId, String number, int textColor) {
        TextView tabText;
        tabText = tab.getCustomView().findViewById(R.id.tab_counter);
        tabText.setText(number);
        tabText.setTextColor(ResourcesCompat.getColor(getResources(), textColor, null));
        tabText.setBackground(ResourcesCompat.getDrawable(getResources(), drawableId, null));
    }

    private class ViewPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeTabItemLayout(tabLayout.getTabAt(position),R.drawable.checked_circle_selector,String.valueOf(position + 1),R.color.colorPrimary);
            for (int i = 0; i < position ; i++) {
                changeTabItemLayout(tabLayout.getTabAt(i),R.drawable.complete_tab, "\u2713",R.color.black);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void setTabView() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View view = ((TabPageAbstractAdapter)viewPager.getAdapter()).getTabView(i);
                tab.setCustomView(view);
                if (i == tabLayout.getTabCount() - 1) {
                    tab.getCustomView().findViewById(R.id.tab_divider).setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void disableTabsClicking() {
        //disable tabs click action
        LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
        tabStrip.setEnabled(false);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setClickable(false);
        }
    }

}

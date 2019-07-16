package com.simple.view;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.xcheng.view.adapter.TabInfo;
import com.xcheng.view.controller.EasyPagerActivity;

import java.util.List;


public class TabSmartActivity extends EasyPagerActivity {


    @Override
    public int getLayoutId() {
        return R.layout.ac_tab;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 0) {
//                    mIndicator.setIndicatorColor(Color.YELLOW);
//                    mIndicator.setIndicatorHeight(20);
//                    mIndicator.setDividerColor(Color.BLUE);
//                } else if (position == 1) {
//                    mIndicator.setIndicatorColor(Color.GREEN);
//                    mIndicator.setIndicatorHeight(15);
//                    mIndicator.setDividerColor(Color.CYAN);
//
//
//                } else if (position == 2) {
//                    mIndicator.setIndicatorColor(Color.RED);
//                    mIndicator.setIndicatorHeight(10);
//                    mIndicator.setDividerColor(Color.LTGRAY);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    @Override
    protected void getTabInfos(List<TabInfo> tabInfos) {
        tabInfos.add(new TabInfo("0", "新闻", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("1", "咨询", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("2", "视频", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("3", "本地", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("4", "小说", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("5", "阅读", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("6", "本地", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("7", "贴吧", RefreshSmtFragment.class));
        tabInfos.add(new TabInfo("8", "评论", RefreshSmtFragment.class));
    }
}

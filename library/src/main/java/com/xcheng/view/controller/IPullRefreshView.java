package com.xcheng.view.controller;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xcheng.view.adapter.HEFAdapter;
import com.xcheng.view.pullrefresh.LoadingState;

import java.util.List;

/**
 * T 代表http请求返回的数据
 * Created by cx on 2016/10/13.
 */
public interface IPullRefreshView<T> extends HEFAdapter.OnBindHolderListener {
    /**
     * Http 请求
     *
     * @param isRefresh 是否为刷新
     */
    @UiThread
    void requestData(boolean isRefresh);

    @UiThread
    void refreshView(boolean isRefresh, List<T> data);

    /**
     * 加载数据结束之后复位UI
     *
     * @param isRefresh 是否为刷新
     * @param state     加载状态
     */
    @UiThread
    void complete(boolean isRefresh, LoadingState state);

    /***
     * 获取EmptyView 如果为空不设置
     */
    @Nullable
    View getEmptyView(ViewGroup parent);

    /***
     * 获取HeaderView ,如果为空不设置
     */
    @Nullable
    View getHeaderView(ViewGroup parent);

    /***
     * 获取FooterView,显示加载更多的状态 如果为空不设置
     */
    @Nullable
    View getFooterView(ViewGroup parent);

    @NonNull
    HEFAdapter<T> getHEFAdapter();

    /**
     * 每页的长度
     *
     * @return limit
     */
    @IntRange(from = 1)
    int getLimit();

    /**
     * for recyclerView
     */
    @NonNull
    RecyclerView.LayoutManager getLayoutManager();

    /**
     * 为null的情况下 不设置ItemDecoration
     *
     * @return ItemDecoration
     */
    @Nullable
    RecyclerView.ItemDecoration getItemDecoration();

    /**
     * 为null的情况下 没有ItemAnimator
     *
     * @return ItemAnimator
     */
    @Nullable
    RecyclerView.ItemAnimator getItemAnimator();
}

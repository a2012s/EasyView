package com.xcheng.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 抽离Adapter适配器接口
 * Created by chengxin on 2017/9/5.
 */
public interface IAdapterDelegate<T, VH extends RecyclerView.ViewHolder> {
    /**
     * 获取 itemView布局，这里不直接用getLayoutId的好处是可以兼容在代码里面构建的View，
     * 而且可以做一些不用重复初始化的设置，如设置背景、字体、监听等，
     * 无需在绑定数据复用的时候重新设置
     * {@link android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     *
     * @return itemView
     */
    View getItemView(ViewGroup parent, int viewType);

    /**
     * 获取 对应position中的View类型
     * {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *
     * @param t        对应位置上的数据
     * @param position position to query
     * @return viewType
     */
    int getViewType(T t, int position);

    /**
     * 绑定数据
     * {@link android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}
     *
     * @param position position onBindViewHolder 所指定的position
     */
    void convert(VH holder, T t, int position);

}
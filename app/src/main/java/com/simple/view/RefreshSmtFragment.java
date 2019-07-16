package com.simple.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xcheng.view.adapter.EasyAdapter;
import com.xcheng.view.adapter.EasyHolder;
import com.xcheng.view.adapter.SpaceDecoration;
import com.xcheng.view.controller.SmartRefreshFragment;
import com.xcheng.view.util.LocalDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengxin on 2017/9/3.
 */
public class RefreshSmtFragment extends SmartRefreshFragment<String> {
    @Override
    public int getLayoutId() {
        return R.layout.layout_smart_refresh;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mAdapter.setOnItemClickListener(new EasyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EasyHolder holder, int position) {
//                ToastLess.showToast(position + "==position:");
//                mAdapter.notifyFooter();
                int beforeAp = holder.getAdapterPosition();
                Log.e("print", "beforeAp:" + beforeAp);
                int beforeLp = holder.getLayoutPosition();
                Log.e("print", "beforeLp:" + beforeLp);


                int afterAp = holder.getAdapterPosition();
                Log.e("print", "afterAp:" + afterAp);

                int afterLp = holder.getLayoutPosition();
                Log.e("print", "afterLp:" + afterLp);
            }
        });
        mRecyclerView.addItemDecoration(new SpaceDecoration(LocalDisplay.dp2px(10)));
    }

    @NonNull
    @Override
    protected Config getConfig() {
        return new Config(200, 8);
    }

    @Override
    public void requestData(final boolean isRefresh) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRefresh) {
                    List<String> data = new ArrayList<>();
                    for (int index = 0; index < 8; index++) {
                        data.add("数据：" + index);
                    }
                    refreshView(true, data);
                } else {
                    List<String> data = new ArrayList<>();
                    if (mAdapter.getDataCount() < 40) {
                        for (int index = 0; index < 8; index++) {
                            data.add("数据：" + index);
                        }
                    } else {
                        for (int index = 0; index < 2; index++) {
                            data.add("数据：" + index);
                        }
                    }
                    refreshView(false, data);
                }
            }
        }, 500);
    }

    @NonNull
    @Override
    public EasyAdapter<String> createAdapter() {
        return new EasyAdapter<String>(getContext(), R.layout.ev_item_text) {
            @Override
            public void convert(EasyHolder holder, String s, int position) {
                // Log.e("print", "adapterPosition:" + holder.getAdapterPosition());
                TextView textView = (TextView) holder.itemView;
                textView.setText(s);
            }
        };
    }
}

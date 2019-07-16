package com.xcheng.view.util;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xcheng.view.R;
import com.xcheng.view.adapter.EasyAdapter;
import com.xcheng.view.adapter.EasyHolder;
import com.xcheng.view.controller.EasyDialog;
import com.xcheng.view.widget.CheckView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建时间：2018/10/31
 * 编写人： chengxin
 * 功能描述：切换环境使用
 */
public class SwitcherDialog extends EasyDialog {
    private final List<Module> mModules = new ArrayList<>();
    public static String TITLE = "请选择测试环境";
    private EasyAdapter<Module> mAdapter;
    private OnSwitcherListener mOnSwitcherListener;
    //保存当前模块的环境
    private final Map<String, String> mCurEnvironments = new LinkedHashMap<>(2);
    private final Map<String, String> mOriginals = new LinkedHashMap<>(2);

    public void addModule(String name, @Nullable String curEnvironment, @Size(min = 1) String[] environments) {
        Preconditions.checkNotNull(name, "name==null");
        Preconditions.checkNotNull(environments, "environments==null");
        if (curEnvironment != null) {
            mCurEnvironments.put(name, curEnvironment);
            mOriginals.put(name, curEnvironment);
        }
        mModules.add(new Module(name, null));
        for (String environment : environments) {
            mModules.add(new Module(name, environment));
        }
    }

    public SwitcherDialog(@NonNull Context context) {
        super(context, R.style.ev_dialog_commonStyle);
        setCancelable(false);
        //如果有title会屏掉掉
        // getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    public void setOnSwitcherListener(OnSwitcherListener onSwitcherListener) {
        this.mOnSwitcherListener = onSwitcherListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ev_dialog_switcher;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.ev_id_recyclerView);
        TextView title = findViewById(R.id.ev_id_titleView);
        title.setText(TITLE);
        final int blueColor = ContextCompat.getColor(getContext(), R.color.ev_light_blue);
        final int greyColor = ContextCompat.getColor(getContext(), R.color.ev_text_grey);

        mAdapter = new EasyAdapter<Module>(getContext(), mModules, R.layout.ev_item_module) {

            @Override
            public void convert(EasyHolder holder, Module module, int position) {
                TextView tvNameOrEnvironment = holder.getView(R.id.tv_id_name_or_environment);
                CheckView checkView = holder.getView(R.id.cv_isChecked);
                if (module.environment == null) {
                    tvNameOrEnvironment.setText(module.name);
                    tvNameOrEnvironment.setTextSize(26);
                    checkView.setVisibility(View.GONE);
                    tvNameOrEnvironment.setTextColor(blueColor);
                } else {
                    tvNameOrEnvironment.setTextSize(24);
                    tvNameOrEnvironment.setTextColor(greyColor);
                    tvNameOrEnvironment.setText(module.environment);
                    checkView.setVisibility(View.VISIBLE);
                    checkView.setChecked(false);
                    if (mOnSwitcherListener != null) {
                        String environment = mCurEnvironments.get(module.name);
                        if (environment != null && environment.equals(module.environment)) {
                            checkView.setChecked(true);
                            tvNameOrEnvironment.setTextColor(blueColor);
                        }
                    }
                }
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        findViewById(R.id.ev_id_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnSwitcherListener != null) {
                    boolean hasChanged = false;
                    for (Map.Entry<String, String> entry : mCurEnvironments.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        String originalValue = mOriginals.get(key);
                        hasChanged = !originalValue.equals(value);
                        if (hasChanged) {
                            break;
                        }
                    }
                    mOnSwitcherListener.onSwitcher(hasChanged, mCurEnvironments);
                }
            }
        });
        mAdapter.setOnItemClickListener(new EasyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EasyHolder holder, int adapterPosition) {
                Module module = mModules.get(adapterPosition);
                if (module.environment == null)
                    return;
                mCurEnvironments.put(module.name, module.environment);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public interface OnSwitcherListener {
        void onSwitcher(boolean hasChanged, Map<String, String> curEnvironments);
    }

    public static class Module {
        public final String name;
        //如果为null标识只显示标题
        public final String environment;

        public Module(String name, @Nullable String environment) {
            this.name = name;
            this.environment = environment;
        }
    }
}

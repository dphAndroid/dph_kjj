package kj.dph.com.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import kj.dph.com.R;
import kj.dph.com.base.BaseActivity;
import kj.dph.com.ui.adapter.StatusManagerAdapter;
import kj.dph.com.view.statusManager.StatusManager;
import kj.dph.com.widget.refreshlayout.RefreshLayout;

/**
 * Created by ${lgy} on 2018/4/1115:57
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class StatusManagerActivity extends BaseActivity implements RefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_status_manager)
    RecyclerView rvStatusManager;
    @BindView(R.id.btn_error)
    Button btnError;
    @BindView(R.id.btn_content)
    Button btnContent;
    @BindView(R.id.btn_empty)
    Button btnEmpty;
    @BindView(R.id.refreshlayout)
    RefreshLayout refreshlayout;
    private StatusManager statuManager;
    StatusManagerAdapter statusManagerAdapter;
    List<String> list = new ArrayList<>();


    @Override
    public void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        setLayoutView(R.layout.actiivty_status_mananger, "哈哈哈");
    }

    @Override
    public void initView() {
        super.initView();
        rvStatusManager.setLayoutManager(new LinearLayoutManager(this));
        statusManagerAdapter = new StatusManagerAdapter(this, R.layout.item_demo, list);
        rvStatusManager.setAdapter(statusManagerAdapter);
        refreshlayout.setOnRefreshListener(this);
        refreshlayout.setHeaderColorSchemeColors(blue, red, green, yellow);
        refreshlayout.setFooterColorSchemeColors(blue, red, green, yellow);
        setStatusManager();
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i < 10; i++) {
            list.add("转台数据" + i);
        }
        statusManagerAdapter.notifyDataSetChanged();
    }

    public void setStatusManager() {
        statuManager = new StatusManager.Builder(refreshlayout).setEmptyContent("从页面").setErrorContent("错误页面").setOnEmptyClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).setOnErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).create();
    }

    @OnClick({R.id.btn_error, R.id.btn_content, R.id.btn_empty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_error:
                statuManager.showErrorLayout();
                break;
            case R.id.btn_content:
                statuManager.showSuccessLayout();
                break;
            case R.id.btn_empty:
                statuManager.showEmptyLayout();
                break;
        }
    }

    @Override
    public void onHeaderRefresh() {
        refreshlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                statusManagerAdapter.notifyDataSetChanged();
                refreshlayout.setHeaderRefreshing(false);
            }
        }, 500);
    }

    @Override
    public void onFooterRefresh() {
        refreshlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                statusManagerAdapter.notifyDataSetChanged();
                refreshlayout.setFooterRefreshing(false);
            }
        }, 500);
    }
}

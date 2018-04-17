package kj.dph.com.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kj.dph.com.R;
import kj.dph.com.base.BaseActivity;
import kj.dph.com.common.Constants;
import kj.dph.com.ui.adapter.DemoAdapter;
import kj.dph.com.util.ToastUtil;
import kj.dph.com.util.Utils;
import kj.dph.com.util.logUtil.LogUtil;
import kj.dph.com.util.logUtil.LogUtilYxy;
import kj.dph.com.widget.refreshlayout.RefreshLayout;

/**
 * Created by yxy on 2018/4/17 0017.
 */

public class XmlActivity extends BaseActivity implements RefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_home_yygh)
    RecyclerView rvHomeYygh;
    @BindView(R.id.refreshlayout)
    RefreshLayout refreshlayout;
    private ArrayList<String> list = new ArrayList();
    private DemoAdapter adapter;

    double x;
    private int[] a = {320, 480, 540, 600, 768, 720, 800, 1400, 1080, 1440, 1536};

    @Override
    public void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        setLayoutView(R.layout.activity_main, "哈哈哈");
    }

    @Override
    public void initView() {
        super.initView();
        adapter = new DemoAdapter(this, R.layout.item_demo, list);
        refreshlayout.setOnRefreshListener(this);
        refreshlayout.setHeaderColorSchemeColors(blue, red, green, yellow);
        refreshlayout.setFooterColorSchemeColors(blue, red, green, yellow);

        rvHomeYygh.setLayoutManager(new LinearLayoutManager(this));
        rvHomeYygh.setItemAnimator(new DefaultItemAnimator());
        rvHomeYygh.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i < a.length; i++) {
            list.add(a[i] + "");
        }
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                for (int i = 1; i <= 1242; i++) {
                    x = a[position] * i / 1242.00;

                    String fileName = "log"+a[position]+".txt";

                    writeTxtToFile("<dimen name=\"x" + i + "\">" + totalMoney(x) + "px</dimen>", Constants.SDPATH+"/aaa/", fileName);
                }
                ToastUtil.showShort("do well");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public static String totalMoney(double money) {
        java.math.BigDecimal bigDec = new java.math.BigDecimal(money);
        double total = bigDec.setScale(2, java.math.BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(total);
    }


    // 将字符串写入到文本文件中
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                LogUtil.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            LogUtil.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    @Override
    public void onHeaderRefresh() {

    }

    @Override
    public void onFooterRefresh() {

    }
}

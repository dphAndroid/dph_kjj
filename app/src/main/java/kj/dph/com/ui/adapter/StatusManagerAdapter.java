package kj.dph.com.ui.adapter;

import android.content.Context;
import android.content.Intent;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import kj.dph.com.R;

/**
 * Created by yxy on 2016/7/26 0026.
 */
public class StatusManagerAdapter extends CommonAdapter<String> {

    private Intent intent = new Intent();
    private Context context;

    public StatusManagerAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, String bean, int i) {
        holder.setText(R.id.tv_title, bean);
    }
}

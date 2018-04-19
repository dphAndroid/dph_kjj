package kj.dph.com.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import kj.dph.com.R;
import kj.dph.com.util.imageUtil.ImageLoaderProxy;
import kj.dph.com.util.logUtil.LogUtilYxy;
import kj.dph.com.view.CircularImage;

/**
 * Created by yxy on 2016/7/26 0026.
 */
public class DemoAdapter extends CommonAdapter<String> {

    public DemoAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String bean, int i) {
        ImageView iv_pic = holder.getView(R.id.iv_pic);
        ImageLoaderProxy.getInstance().displayImage(mContext,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524132422464&di=126bb411977b901bbc145d6143b1f925&imgtype=0&src=http%3A%2F%2Ffile06.16sucai.com%2F2016%2F0330%2Fdaa1f8933c2ae7f54760a9a5dba5e08c.jpg" ,iv_pic );
        holder.setText(R.id.tv_title, bean);
    }
}

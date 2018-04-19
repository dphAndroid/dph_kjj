package kj.dph.com.ui.adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kj.dph.com.R
import kj.dph.com.util.imageUtil.ImageLoaderProxy

/**
 * Created by yxy on 2018/4/19 0019.
 */

class  DemoKotlinAdapter(context: Context , layoutId: Int , datas :List<String> ):CommonAdapter<String>(context, layoutId, datas){
    override fun convert(holder: ViewHolder?, t: String?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        var iv =  holder!!.getView<ImageView>(R.id.iv_pic)
        ImageLoaderProxy.getInstance().displayImage(mContext,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524132422464&di=126bb411977b901bbc145d6143b1f925&imgtype=0&src=http%3A%2F%2Ffile06.16sucai.com%2F2016%2F0330%2Fdaa1f8933c2ae7f54760a9a5dba5e08c.jpg",iv)

    }

}

package kj.dph.com.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import kj.dph.com.R;


/**
 * Created by yxy on 2017/6/17 0017.
 */

public class ItemMainView extends FrameLayout {
    public ItemMainView(@NonNull Context context) {
        super(context);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_demo, this);
    }

    public synchronized void setTitle(String title, boolean isSub) {
        if (isSub) {
            ((TextView) (findViewById(R.id.tv_title))).setText("" + title);
        } else {
            ((TextView) (findViewById(R.id.title))).setText(title);
        }
    }


    public synchronized void setTitleId(int titleId, boolean isSub) {
        String title = this.getResources().getString(titleId);
        if (isSub) {
            ((TextView) (findViewById(R.id.title))).setText("   " + title);
        } else {
            ((TextView) (findViewById(R.id.title))).setText(title);
        }
    }

}

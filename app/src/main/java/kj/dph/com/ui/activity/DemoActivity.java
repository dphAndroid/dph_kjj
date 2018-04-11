package kj.dph.com.ui.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import kj.dph.com.R;
import kj.dph.com.view.ItemMainView;


/**
 * Created by yxy on 2017/6/17 0017.
 * 首页  列表页显示所有包含的页面
 */

public class DemoActivity extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ListAdapter adapter = new CustomArrayAdapter(this.getApplicationContext(), demos);
        setListAdapter(adapter);
    }

    private DemoDetails[] demos = {
            new DemoDetails("Kotlin练习", MainActivity.class), new DemoDetails("空页面数据", StatusManagerActivity.class)
    };

    private static class DemoDetails {
        int titleId;
        int descriptionId;
        String title;
        Class<? extends Activity> activityClass;

        public DemoDetails(int titleId, int descriptionId, Class<? extends Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.descriptionId = descriptionId;
            this.activityClass = activityClass;
        }

        public DemoDetails(String title, Class<? extends Activity> activityClass) {
            super();
            this.title = title;
            this.activityClass = activityClass;
        }
    }

    private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {
        public CustomArrayAdapter(Context context, DemoDetails[] demos) {
            super(context, R.layout.item_demo, R.id.title, demos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ItemMainView itemMainView;
            if (convertView instanceof ItemMainView) {
                itemMainView = (ItemMainView) convertView;
            } else {
                itemMainView = new ItemMainView(getContext());
            }
            DemoDetails demoDetails = getItem(position);
            if (demoDetails.titleId == 0) {
                itemMainView.setTitle(demoDetails.title, demoDetails.activityClass != null);
            } else {
                itemMainView.setTitleId(demoDetails.titleId, demoDetails.activityClass != null);
            }
            return itemMainView;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        DemoDetails demoDetails = (DemoDetails) getListAdapter().getItem(position);
        if (demoDetails.activityClass != null) {
            startActivity(new Intent(this.getApplicationContext(), demoDetails.activityClass));
        }
        super.onListItemClick(l, v, position, id);
    }
}

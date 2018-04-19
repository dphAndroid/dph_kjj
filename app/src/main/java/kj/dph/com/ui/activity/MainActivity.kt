package kj.dph.com.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView
import com.xidian.xalertdialog.R.id.x
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kj.dph.com.R
import kj.dph.com.base.BaseActivity
import kj.dph.com.common.Constants
import kj.dph.com.ui.activity.XmlActivity.totalMoney
import kj.dph.com.ui.adapter.DemoAdapter
import kj.dph.com.util.ToastUtil
import kj.dph.com.widget.refreshlayout.RefreshLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), RefreshLayout.OnRefreshListener {

    var isScroll = true
    var list: ArrayList<String> = ArrayList()
    var adapter: DemoAdapter? = null
    var a = arrayOf(320, 480, 540, 600, 768, 720, 800, 1080, 1400, 1440, 1536)
    override fun setCustomLayout(savedInstanceState: Bundle?) {
        super.setCustomLayout(savedInstanceState)
        setLayoutView(R.layout.activity_main, "哈哈哈");
    }

    override fun initView() {
        super.initView()
        adapter = DemoAdapter(this, R.layout.item_demo, list)
        refreshlayout.setOnRefreshListener(this)
        refreshlayout.setHeaderColorSchemeColors(blue, red, green, yellow)
        refreshlayout.setFooterColorSchemeColors(blue, red, green, yellow)
        rv_home_yygh.layoutManager = LinearLayoutManager(this)
        rv_home_yygh.itemAnimator = DefaultItemAnimator()
        rv_home_yygh.adapter = adapter
        adapter!!.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


        rv_home_yygh.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    isScroll = false
                }
            }

        })
    }

    override fun initData() {
        super.initData()
        for (item in 0..a.size - 1) {
            list.add("张三" + a.get(item))
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onHeaderRefresh() {
        refreshlayout.postDelayed(Runnable {
            kotlin.run {
                list.clear()
                for (item in 0..a.size - 1) {
                    list.add("张三" + a.get(item))
                }
                adapter?.notifyDataSetChanged()
                refreshlayout.isHeaderRefreshing = false
            }
        }, 1000)
    }

    override fun onFooterRefresh() {
        refreshlayout.postDelayed(Runnable {
            kotlin.run {
                for (item in 0..a.size - 1) {
                    list.add("张三" + a.get(item))
                }
                adapter?.notifyDataSetChanged()
                refreshlayout.isFooterRefreshing = false
            }
        }, 1000)

    }
}

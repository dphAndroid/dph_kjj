package kj.dph.com.view.statusManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import kj.dph.com.R;

/**
 * Created by ${lgy} on 2018/4/1113:38
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class StatusManager {

    /**
     * 空页面需要的布局 和显示文字
     */
    private String mEmptyContent;//空白内容显示文字

    private String mEmptyBtnText;//空内容按钮显示内容

    private int mEmptyLayoutViewId;//空页面显示的布局Id

    private View mEmptyLayoutView;//空页面显示的布局和内容

    private View.OnClickListener onEmptyClickListener;

    /**
     * 错误页面内容需要显示的内容和文字
     */
    private String mErrorContent;//错误内容显示文字

    private int mErrorResId;//错误资源Id

    private String mErrorBtnText;//错误按钮显示内容

    private int mErrorLayoutViewId;//错误页面布局Id

    private View mErrorLayoutView;//错误页面布局

    private View.OnClickListener onErrorClickListener;//错误监听

    /**
     * 内容页面布局
     */
    private View mContentLayoutView;//内容页面布局

    private final ReplaceLayoutHelper replaceLayoutHelper;

    private LayoutInflater inflater;//加载布局


    public StatusManager(Builder builder) {
        /*空布局信息*/
        this.mEmptyLayoutViewId = builder.getmEmptyLayoutViewId();
        this.mEmptyBtnText = builder.getmEmptyBtnText();
        this.mEmptyContent = builder.getmEmptyContent();
        /*错误信息*/
        this.mErrorLayoutViewId = builder.getmErrorLayoutViewId();
        this.mErrorContent = builder.getmErrorContent();
        this.mErrorBtnText = builder.getmErrorBtnText();
        this.mErrorResId = builder.getmErrorResId();
        this.mContentLayoutView = builder.getContentLayoutView();
        this.onErrorClickListener = builder.getOnErrorClickListener();
        this.onEmptyClickListener = builder.getOnEmptyClickListener();
        this.replaceLayoutHelper = new ReplaceLayoutHelper(mContentLayoutView);
    }


    /**
     * 显示原有布局
     */
    public void showSuccessLayout() {
        replaceLayoutHelper.restoreLayout();
    }

    /**
     * 显示错误页面
     */
    /**
     * 显示空数据布局
     */
    public void showErrorLayout() {
        creatErrorLayout();
        replaceLayoutHelper.showStatusLayout(mErrorLayoutView);
    }

    /**
     * 创建空页面布局和Id
     */
    private void creatErrorLayout() {
        if (mErrorLayoutView == null) {
            mErrorLayoutView = inflateView(mErrorLayoutViewId);
        }
        TextView tvStatusManagerErrorContent = (TextView) mErrorLayoutView.findViewById(R.id.tv_status_manager_content);
        Button btnStatusManagerError = (Button) mErrorLayoutView.findViewById(R.id.btn_status_manager_error);
        ImageView ivStatusManagerError = (ImageView) mErrorLayoutView.findViewById(R.id.iv_status_manager_error);
        tvStatusManagerErrorContent.setText(mErrorContent);
        ivStatusManagerError.setImageResource(mErrorResId);
        btnStatusManagerError.setText(mErrorBtnText);
        btnStatusManagerError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onErrorClickListener != null) {
                    onErrorClickListener.onClick(view);
                }
            }
        });
    }

    /**
     * 显示空数据布局
     */
    public void showEmptyLayout() {
        createEmptyLayout();
        replaceLayoutHelper.showStatusLayout(mEmptyLayoutView);
    }

    /**
     * 创建空页面布局和Id
     */
    private void createEmptyLayout() {
        if (mEmptyLayoutView == null) {
            mEmptyLayoutView = inflateView(mEmptyLayoutViewId);
        }
        TextView tvStatusManagerEmptyContent = (TextView) mEmptyLayoutView.findViewById(R.id.tv_status_manager_empty_content);
        Button btnStatusManagerEmpty = mEmptyLayoutView.findViewById(R.id.btn_status_manager_empty);
        tvStatusManagerEmptyContent.setText(mEmptyContent);
        btnStatusManagerEmpty.setText(mEmptyBtnText);
        btnStatusManagerEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onEmptyClickListener != null) {
                    onEmptyClickListener.onClick(view);
                }
            }
        });
    }

    public static final class Builder {
        /**
         * 空页面需要的布局 和显示文字
         */
        private String mEmptyContent;//空白内容显示文字

        private String mEmptyBtnText;//空内容按钮显示内容

        private int mEmptyLayoutViewId;//空页面显示的布局和内容

        private View.OnClickListener onEmptyClickListener;

        /**
         * 错误页面内容需要显示的内容和文字
         */
        private String mErrorContent;//错误内容显示文字

        private int mErrorResId;//错误资源Id

        private String mErrorBtnText;//错误按钮显示内容

        private int mErrorLayoutViewId;//错误页面布局

        private View.OnClickListener onErrorClickListener;//错误监听

        /**
         * 内容页面布局
         */
        private View mContentLayoutView;//内容页面布局


        private Context mContext;


        public Builder(@NonNull View mContentLayoutView) {
            this.mContentLayoutView = mContentLayoutView;
            mContext = mContentLayoutView.getContext();
            /*设置空页面数据*/
            mEmptyContent = "内容为空";
            mEmptyBtnText = "重新加载";
            mEmptyLayoutViewId = R.layout.layout_empty;
            /*设置错误页面数据*/
            mErrorContent = "网络加载失败";
            mErrorBtnText = "重新加载";
            mErrorResId = R.mipmap.ic_error;
            mErrorLayoutViewId = R.layout.layout_error;
        }

        public String getmEmptyContent() {
            return mEmptyContent;
        }

        public Builder setEmptyContent(String mEmptyContent) {
            this.mEmptyContent = mEmptyContent;
            return this;
        }

        public String getmEmptyBtnText() {
            return mEmptyBtnText;
        }

        public Builder setEmptyBtnText(String mEmptyBtnText) {
            this.mEmptyBtnText = mEmptyBtnText;
            return this;
        }

        public String getmErrorContent() {
            return mErrorContent;
        }

        public Builder setErrorContent(String mErrorContent) {
            this.mErrorContent = mErrorContent;
            return this;
        }

        public int getmErrorResId() {
            return mErrorResId;
        }

        public Builder setErrorResId(int mErrorResId) {
            this.mErrorResId = mErrorResId;
            return this;
        }

        public String getmErrorBtnText() {
            return mErrorBtnText;
        }

        public Builder setmErrorBtnText(String mErrorBtnText) {
            this.mErrorBtnText = mErrorBtnText;
            return this;
        }

        public int getmEmptyLayoutViewId() {
            return mEmptyLayoutViewId;
        }

        public int getmErrorLayoutViewId() {
            return mErrorLayoutViewId;
        }

        public View getContentLayoutView() {
            return mContentLayoutView;
        }


        public View.OnClickListener getOnEmptyClickListener() {
            return onEmptyClickListener;
        }

        public Builder setOnEmptyClickListener(View.OnClickListener onEmptyClickListener) {
            this.onEmptyClickListener = onEmptyClickListener;
            return this;
        }

        public View.OnClickListener getOnErrorClickListener() {
            return onErrorClickListener;
        }

        public Builder setOnErrorClickListener(View.OnClickListener onErrorClickListener) {
            this.onErrorClickListener = onErrorClickListener;
            return this;
        }


        /**
         * 创建状态布局管理器
         *
         * @return 状态布局管理器
         */
        @NonNull
        public StatusManager create() {
            return new StatusManager(this);
        }
    }

    public View inflateView(@NonNull int layoutId) {
        if (inflater == null) {
            inflater = LayoutInflater.from(mContentLayoutView.getContext());
        }
        return inflater.inflate(layoutId, null);
    }
}

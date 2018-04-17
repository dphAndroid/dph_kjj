package kj.dph.com.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import kj.dph.com.R;
import kj.dph.com.base.BaseActivity;
import kj.dph.com.common.Constants;
import kj.dph.com.network.entity.api.BaseLoginApi;
import kj.dph.com.network.entity.request.LoginReq;
import kj.dph.com.network.entity.response.LoginRes;
import kj.dph.com.util.AppManager;
import kj.dph.com.util.RegularUtils;
import kj.dph.com.util.SPUtil;
import kj.dph.com.util.StringUtil;
import kj.dph.com.util.ToastUtil;
import kj.dph.com.util.Utils;
import kj.dph.lib.retrofit.utils.LoginUtil;
import kj.dph.lib.retrofit.utils.PermissionUtils;

/**
 * Created by ${lgy} on 2017/8/1110:14
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.ev_account)
    EditText evAccount;
    @BindView(R.id.ev_pwd)
    EditText evPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private boolean is_only_login;//是否仅仅登录
    private boolean isApply = false;//是否申请权限
    private boolean isNeedReturnInfo = false;//

    private boolean isSetAccount = false;//是否第一次设置账户

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        setLayoutView(R.layout.activity_login, "登录");
        showTitle(false);
        is_only_login = getIntent().getBooleanExtra(Constants.IS_ONLY_LOGIN, false);
        requstCode = getIntent().getIntExtra("requstCode", 0);
        if (requstCode == 0) {
            isNeedReturnInfo = false;
        } else {
            isNeedReturnInfo = true;
        }

    }

    private LoginReq loginReq;
    private BaseLoginApi baseLoginApi;
//    private BaseUserInfoApi baseUserInfoApi;

    @Override
    public void initView() {
        super.initView();
        baseLoginApi = new BaseLoginApi();
//        baseUserInfoApi = new BaseUserInfoApi();
        evAccount.addTextChangedListener(new LoginTextWatcher(evAccount));
        evPwd.addTextChangedListener(new LoginTextWatcher(evPwd));

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void initData() {
        super.initData();
        String phone = (String) SPUtil.get(Constants.PHONE_NUMBER, "");
        evAccount.setText(phone);
        evPwd.setText("");
    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.tv_forget_pwd, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                PermissionUtils.requestPermissionsResult(this, 1000,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        new PermissionUtils.OnPermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                String mPhone = evAccount.getText().toString();
                                String mPwd = evPwd.getText().toString();
                                if (StringUtil.isEmpty(mPhone)) {
                                    ToastUtil.showShort(LoginActivity.this, "请输入手机号");
                                    return;
                                }
                                if (!mPhone.matches(RegularUtils.PHONE)) {
                                    ToastUtil.showShort(LoginActivity.this, "输入的手机号有误，请重新输入");
                                    return;
                                }
                                if (mPwd.length() < 6 || mPwd.length() > 16) {
                                    ToastUtil.showShort(LoginActivity.this, "密码格式有误，请设置6-16位字符");
                                    return;
                                }
                                loginReq = new LoginReq();
                                loginReq.cellphoneNumber = mPhone;
                                loginReq.password = mPwd;
                                baseLoginApi.req = loginReq;
                                httpManager.doHttpDeal(baseLoginApi);
                            }

                            @Override
                            public void onPermissionDenied() {
                                isApply = false;
                                ToastUtil.showShort("读取联系人功能未开启");
                            }
                        });

                break;
            case R.id.btn_register:
//                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_pwd:
//                startActivity(new Intent(this, MyLoginForgetPwdActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void processSuccessResult(String resulte, String mothead) {
        super.processSuccessResult(resulte, mothead);
        if (mothead.equals(baseLoginApi.getMothed())) {
            LoginRes loginRes = JSONObject.parseObject(resulte, LoginRes.class);
            LoginUtil.setLoginBean(this, loginRes);
            ToastUtil.showShort("登录成功");
            if (isNeedReturnInfo) {
                setResult(requstCode);
                finish();
            } else {
//                httpManager.doHttpDeal(baseUserInfoApi);
            }
        }
       /* if (mothead.equals(baseUserInfoApi.getMothed())) {
            final UserInfoRes userInfoRes = JSONObject.parseObject(resulte, UserInfoRes.class);
            UserInfoUtil.saveUser(this, userInfoRes);
            DoctorSPUtil.put(Constants.PHONE_NUMBER, userInfoRes.cellphoneNumber);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    doDevicesSign(true);
                    Utils.doLogin(LoginActivity.this, userInfoRes, is_only_login);
                }
            }, 500);
        }*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(-1);
    }

    @Override
    protected void processFalResult(int httpCode, String result, String mothead) {
        super.processFalResult(httpCode, result, mothead);
    }

    /**
     * 活动启动时需复写该方法
     *
     * @param context
     */
    public static void actionStart(Context context, boolean is_only_login) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("is_only_login", is_only_login);
        context.startActivity(intent);
    }

    private int requstCode;

    /**
     * 活动启动时需复写该方法
     */
   /* public static void actionStartForResult(Activity activity, BaseFragment fragment, int requstCode, boolean is_only_login) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("is_only_login", is_only_login);
        intent.putExtra("requstCode", requstCode);
        fragment.startActivityForResult(intent, requstCode);
    }*/

    /**
     * 活动启动时需复写该方法
     */
   /* public static void actionStartForResult(Activity activity, int requstCode, boolean is_only_login) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("is_only_login", is_only_login);
        intent.putExtra("requstCode", requstCode);
        activity.startActivityForResult(intent, requstCode);
    }*/

    class LoginTextWatcher implements TextWatcher {

        private EditText editText;

        public LoginTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (StringUtil.isEmpty(s.toString())) {
                btnLogin.setEnabled(false);
            } else {
                if (StringUtil.isEmpty(evAccount.getText().toString()) || StringUtil.isEmpty(evPwd.getText().toString())) {
                    btnLogin.setEnabled(false);
                } else {
                    btnLogin.setEnabled(true);
                }
                if (evAccount == editText && evAccount.length() == 11) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utils.hideSystemSoftInput(LoginActivity.this);
                        }
                    }, 500);
                }
            }
        }
    }

}

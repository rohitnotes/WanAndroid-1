package com.example.hy.wanandroid.view.mine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hy.wanandroid.R;
import com.example.hy.wanandroid.base.activity.BaseActivity;
import com.example.hy.wanandroid.config.User;
import com.example.hy.wanandroid.contract.mine.LoginContract;
import com.example.hy.wanandroid.di.component.activity.DaggerLoginActivityComponent;
import com.example.hy.wanandroid.di.module.activity.LoginActivityModule;
import com.example.hy.wanandroid.presenter.mine.LoginPresenter;
import com.example.hy.wanandroid.utils.KeyBoardUtil;
import com.example.hy.wanandroid.widget.dialog.LoadingDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_face)
    CircleImageView ivFace;
    @BindView(R.id.at_account)
    AutoCompleteTextView atAccount;
    @BindView(R.id.tl_account)
    TextInputLayout tlAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tl_password)
    TextInputLayout tlPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.cl_login)
    ConstraintLayout clLogin;

    @Inject
    LoginPresenter mPresenter;
    @Inject
    LoadingDialog mLoadingDialog;

    private View focusView = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        DaggerLoginActivityComponent.builder().appComponent(getAppComponent()).loginActivityModule(new LoginActivityModule()).build().inject(this);
        mPresenter.attachView(this);

        ivBack.setOnClickListener(v -> finish());
        tvRegister.setOnClickListener(v -> RegisterActivity.startActivity(this));
        btnLogin.setOnClickListener(v -> {
            // Reset errors.
            tlAccount.setError(null);
            tlPassword.setError(null);
            // Store values at the time of the login attempt.
            KeyBoardUtil.closeKeyBoard(this, atAccount);
            KeyBoardUtil.closeKeyBoard(this, etPassword);
            mPresenter.login(atAccount.getText().toString().trim(), etPassword.getText().toString().trim());
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {
        mLoadingDialog.show(getSupportFragmentManager(), "tag");
    }

    @Override
    public void showNormalView() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showErrorView() {
        mLoadingDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    public void setAccountErrorView(String error) {
        focusView = tlAccount;
        tlAccount.setError(error);
    }

    @Override
    public void setPasswordErrorView(String error) {
        focusView = tlPassword;
        tlPassword.setError(error);
    }

    @Override
    public void requestFocus(boolean cancel) {
        if(focusView == null || !cancel) return;
        focusView.requestFocus();
    }

    @Override
    public void loginSuccess(){
        showToast(getString(R.string.loginActivity_success));
        finish();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}

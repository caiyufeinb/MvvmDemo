package com.example.mvvm_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.Dog;
import com.example.bean.User;
import com.example.mvvm_demo.databinding.ActivityMainBinding;
import com.example.viewmodels.MainViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    private TextInputEditText etAccount, etPwd;
    private TextView tvAccount, tvPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dataBinding双向绑定视图
        ActivityMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        //Model -> View
        //这里这么写是因为横竖屏切换的时候会重走onCreate生命周期，Activity会被销毁重建，但是mainViewModel不会，它的生命周期长于Activity
        //我们可以通过mainViewModel中的user对象是否为空判断是否是第一次创建该Activity，这样就可以保存之前修改后的User数据。
        User user;
        if (mainViewModel.user != null) {
            user = mainViewModel.user.getValue();
        } else {
            user = new User("admin", "1234");
        }
        mainViewModel.getUser().setValue(user);
        //获取观察对象
        MutableLiveData<User> user1 = mainViewModel.getUser();
        //检测到user1对象发生变化的时候设置activity_main界面的数据源为mainViewModel
        user1.observe(this, user2 -> dataBinding.setViewModel(mainViewModel));

        dataBinding.btnLogin.setOnClickListener(v -> {
            if (mainViewModel.user.getValue().getAccount().isEmpty()) {
                Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mainViewModel.user.getValue().getPwd().isEmpty()) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

        });
        /*//DataBinding单向绑定数据绑定视图
        ActivityMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //设置数据，直接显示在xml上
        user = new User("admin", "123456");
        dataBinding.setUser(user);

        dataBinding.btnLogin.setOnClickListener(v -> {
            //通过手动更改 数据源的方式，将更改的数据通知到xml上
            user.setAccount(dataBinding.etAccount.getText().toString());
            user.setPwd(dataBinding.etPwd.getText().toString());
        });*/
        /*LiveData绑定
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        tvAccount = findViewById(R.id.tv_account);
        tvPwd = findViewById(R.id.tv_pwd);
        findViewById(R.id.btn_login).setOnClickListener(v -> {
            mainViewModel.account.setValue(etAccount.getText().toString());
            mainViewModel.pwd.setValue(etPwd.getText().toString());

            if (mainViewModel.account.getValue().isEmpty()) {
                Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mainViewModel.pwd.getValue().isEmpty()) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        });
        //观察数据变化的正常和简化写法
        mainViewModel.account.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String account) {
                tvAccount.setText("账号: " + account);
            }
        });
        mainViewModel.pwd.observe(this, pwd -> tvPwd.setText("密码: " + pwd));*/

        /*Dog dog = new Dog();
        dog.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.name) {
                    Log.e("看看刷新了哪", "刷新了name");
                } else if (propertyId == BR._all) {
                    Log.e("看看刷新了哪", "全部全部");
                } else {
                    Log.e("看看刷新了哪", "错误");
                }
            }
        });*/
    }

}
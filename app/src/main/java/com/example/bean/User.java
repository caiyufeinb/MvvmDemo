package com.example.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvm_demo.BR;


public class User extends BaseObservable {
    private String account;
    private String pwd;

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);//通知改变
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);//通知改变
    }

    public User(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", Object='" + this + '\'' +
                '}';
    }
}

package com.example.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvm_demo.BR;

public class Dog extends BaseObservable {
    //如果是public修饰，直接使用@Bindable注解
    @Bindable
    public String name;
    //如果是private修饰，则在get方法中使用@Bindable，一般推荐使用这种方式
    private String color;

    public void setDataOnlyName(String name, String color) {
        this.name = name;
        this.color = color;
        //只刷新name字段
        notifyPropertyChanged(BR.name);
    }

    public void setDataAll(String name, String color) {
        this.name = name;
        this.color = color;
        //刷新全部字段
        notifyChange();
    }
}

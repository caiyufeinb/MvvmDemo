package com.example.bean;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;


public class Human {
    //ObservableField只支持常量
    //ObservableField<参数类型>,规定参数类型后，set，get，@Bindable，刷新都已经封装好了
    public final ObservableField<String> name = new ObservableField<>();

    //其他也封装了其他基本数据类型，比如int
    public final ObservableInt age = new ObservableInt();

    public Human(String name, int age) {
        this.name.set(name);
        this.age.set(age);
    }
}

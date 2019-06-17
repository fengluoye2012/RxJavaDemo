package com.rxjava_demo;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<String> list;
    private Intent intent;

    protected List<String> initData() {
        list = new ArrayList<>();
        list.add("创建操作符");
        list.add("创建操作符");
        list.add("创建操作符");
        list.add("创建操作符");
        return list;
    }

    @Override
    protected void iniListeners() {
        mainAdapter.setItemViewClick(new MainAdapter.ItemViewClick() {
            @Override
            public void itemClick(int pos) {
                switch (pos) {
                    case 0:
                        intent = new Intent(act,CreateActivity.class);
                        startActivity(intent);
                        break;
                    case 1:

                        break;
                    default:
                        break;
                }
            }
        });
    }
}

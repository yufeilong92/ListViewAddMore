package com.lawyee.studay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<WeakReference<View>> baseView;
    private MyListView mLsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MyAdapter myAdapter = new MyAdapter(this);

        baseView=myAdapter.getBaseView();
        mLsView.setAdapter(myAdapter);
        mLsView.setOnLazyLoadListener(new MyListView.OnLazyLoadListener() {
            @Override
            public void shouldLoad(List<Integer> itemsPos) {

            }
        });

    }


    private void initView() {
        mLsView = (MyListView) findViewById(R.id.ls_view);
    }
}

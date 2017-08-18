package com.lawyee.studay;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private ListView mListView;
    private ArrayList<String> mLists;
    private View view;
    private com.lawyee.studay.listVieAdapter listVieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        view = LayoutInflater.from(this).inflate(R.layout.itemaddmore, null);
        initView();
        bindDataView();
    }

    private void bindDataView() {
        mLists = new ArrayList<>();
        int a = 20;
        for (int i = 0; i < a; i++) {
            mLists.add("第一行数据" + i);
        }

        listVieAdapter = new listVieAdapter(mLists, this);
        mListView.setAdapter(listVieAdapter);
//        mListView.addFooterView(view, null, true);
        mListView.setOnScrollListener(this);


    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        Log.e("===", "onScrollStateChanged: "+absListView.getLastVisiblePosition()+"///"+absListView.getCount() );
        if (absListView.getLastVisiblePosition() == absListView.getCount() - 1) {
            mListView.addFooterView(view, null, true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(getNowPage()+1);
                    mListView.removeFooterView(view);
                    listVieAdapter.notifyDataSetChanged();
                }
            }, 2000);

        }
    }
 /*   *//**
     * 清除数据
     *//*
    private void clearDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList();
        } else {
            mDataList.clear();
        }
    }

    *//**
     * 增加列表数据
     * @param nowPage
     *//*
    private void addDataList(List<?> list) {
        if (mDataList == null) {
            clearDataList();
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        mDataList.addAll(list);
    }*/

    private void loadData(int nowPage) {
        int b = nowPage;
        ArrayList<String> objects = new ArrayList<>();
        for (int i = 0; i < b; i++) {
            objects.add("第二行数据" + i);
        }
        if (objects.size() > 0) {
            mLists.addAll(objects);
        } else {
            mListView.removeFooterView(view);
        }


    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        Log.e("===", "onScroll: "+ i+"//"+i1+"////"+i2+"////");
    }
    /**
     * 默认请求记录数
     */
    public static final int CINT_PAGE_SIZE = 10;
    /**
     * 当前数据有几页
     *
     * @return
     */
    private int getNowPage() {
        if (mLists == null || mLists.isEmpty())
            return 0;
        if (mLists.size() % CINT_PAGE_SIZE == 0)
            return mLists.size() / CINT_PAGE_SIZE;
        else
            return mLists.size() / CINT_PAGE_SIZE + 1;
    }
}

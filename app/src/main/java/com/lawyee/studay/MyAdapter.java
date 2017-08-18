package com.lawyee.studay;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.lawyee.studay
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/17 10:48
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class MyAdapter extends BaseAdapter {

    private static final int TIEM_COUNT = 300;

    private WeakReference weakContext = null;

    private List<WeakReference<View>> views = null;

    private List<String> mDatas;

    private Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        views = new ArrayList<>(TIEM_COUNT);
        for (int i = 0; i < TIEM_COUNT; i++) {
            views.add(null);
        }

    }

    @Override
    public int getCount() {
        return 300;
    }

    @Override
    public Object getItem(int i) {
        return mDatas == null ? null : mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        TextView textView = null;
        if (convertView == null) {
            textView = new TextView((Context) weakContext.get());
            textView.setGravity(Gravity.BOTTOM | Gravity.CENTER);
            textView.setWidth(400);
            textView.setHeight(200);
            convertView = textView;
        } else {
            textView = (TextView) convertView;
            textView.setBackgroundResource(0);
        }

        textView.setTag(position);
        textView.setText(position + ":正在准备加载");
        views.set(position, new WeakReference<View>(textView));
        return textView;
    }
    public List<WeakReference<View>> getBaseView(){
        return views;
    }
}

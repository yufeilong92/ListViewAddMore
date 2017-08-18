package com.lawyee.studay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.lawyee.studay
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/17 11:24
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class listVieAdapter extends BaseAdapter implements AbsListView.OnScrollListener {


    private List<String> mData;
    private Context mContext;
    private final LayoutInflater mInflater;

    private List<String> mAddMoreDate;
    private View view;

    public listVieAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setLoadMoreDate(List<String> date, View view) {
        if (date != null && !date.isEmpty()) {
            this.view = view;
            mAddMoreDate = date;
            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData == null ? null : mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View conview, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (conview == null) {
            conview = mInflater.inflate(R.layout.itemshow, null);
            holder = new ViewHolder(conview);
            conview.setTag(holder);
        } else {
            holder = (ViewHolder) conview.getTag();
        }
        holder.mItShow.setText(mData.get(i));
        return conview;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (absListView.getLastVisiblePosition() == absListView.getCount() - 1) {
            if (mAddMoreDate != null && !mAddMoreDate.isEmpty()) {
                loadData();
                notifyDataSetChanged();
                ((ListView) absListView).removeFooterView(view);

            } else {
                if (view != null)
                    ((ListView) absListView).removeFooterView(view);
            }
        }
    }

    private void loadData() {
        for (int i = 0; i < mAddMoreDate.size(); i++) {
            mData.add(mAddMoreDate.get(i));
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


    }

    public static class ViewHolder {
        public View rootView;
        public TextView mItShow;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mItShow = (TextView) rootView.findViewById(R.id.it_show);
        }
    }
}

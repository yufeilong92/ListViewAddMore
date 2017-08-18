package com.lawyee.studay;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.widget.AbsListView;
import android.widget.ListView;

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
 * @date: 2017/8/17 10:01
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {

    private static final String TAG = "listView";

    private OnScrollListener onScrollListener;

    private OnLazyLoadListener onLazyLoadListener;

    //记录item 的懒加载情况
    //比如（1，true）表示为position 为1的item 已经懒加载过了
    //（2，false）表示postion 为2 的还没有被加载；
    private SparseArray<Boolean> itemSnow;

    //    以前可见的item项
    private int oldVisibleiteCount = 0;


    public MyListView(Context context) {
        super(context);
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 当ListView出现滑动时会回调这个方法
     * 但ListView首次显示时并没有滑动，所以还需要通过onScroll方法判断一下ListView的首次展示
     * scrollState 表示滑动的位置
     */
    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        //设置不需要架加载的item 设置为未加载
        updateShouLoadPos();
        //判断是否有过滑过
        if (scrollState == 0) {

        }

    }

    private void updateShouLoadPos() {

    }

    /***
     * * 当ListView首次加载时，这个方法会调用多次，同时这个方法第一次被
     * 调用时visibleItemCount的值为0，所以可以跟据这个特性判断listView是不是首次显示
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //visibleItemCount判断是不是第一次调用，oldvisileitemCount oldVisibleItemCount
        if (visibleItemCount!=oldVisibleiteCount&&oldVisibleiteCount!=-1){
          //因为第一加载，所以回调此方法，保证不见的不加载
            updateShouldLoadPos();
            //设置应该加载的项为第一个item 以及最后一个item为第一个加上最后一个-1
            caculateWhichShouldLoad(firstVisibleItem,firstVisibleItem+visibleItemCount-1);
            //并且同时把以前可见的最后一个item (现在下拉第一个可见item )设置为-1，那么现在第一个firstVisibleItem有重新为0了。
            oldVisibleiteCount=-1;

        }
        if (oldVisibleiteCount==-1){
            oldVisibleiteCount=visibleItemCount;
        }
        //如何他滑动了，着将回调传递下去，这样不影响在外部调用监听方法
        if (this.onScrollListener!=null){
            this.onScrollListener.onScroll(view,firstVisibleItem,visibleItemCount,totalItemCount);
        }
    }

    private void init() {
        //调用父类的设置滑动监听事件
        super.setOnScrollListener(this);
        itemSnow = new SparseArray<>();
    }

    private void caculateWhichShouldLoad(int firstPos, int finalPos) {
        ArrayList<Integer> itemsPos = new ArrayList<>();
        for (int i = firstPos; i <= finalPos; i++) {
            if (!itemSnow.get(i, false)) {
                itemsPos.add(i);
                itemSnow.put(i, true);
            }
        }
        Log.d(TAG, "需要加载的position" + itemsPos.toString());

        if (onLazyLoadListener != null) {
            onLazyLoadListener.shouldLoad(itemsPos);
        }

    }

    private void updateShouldLoadPos() {
        int first = this.getFirstVisiblePosition();
        int last = this.getLastVisiblePosition();
        for (int i = 0; i < first; i++) {
            itemSnow.put(i, false);
        }
        for (int i = 0; i < last; i++) {
            itemSnow.put(i, false);
        }

    }

    public interface OnLazyLoadListener {
        /**
         * 应该被加载细节的项
         *
         * @param itemsPos
         */
        void shouldLoad(List<Integer> itemsPos);

    }

    public OnLazyLoadListener getOnLazyLoadListener() {
        return onLazyLoadListener;
    }

    public void setOnLazyLoadListener(OnLazyLoadListener onLazyLoadListener) {
        this.onLazyLoadListener = onLazyLoadListener;
    }
}

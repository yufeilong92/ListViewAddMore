package com.lawyee.studay;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.lawyee.studay
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/17 10:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class MyAsyTask extends AsyncTask<Integer, String, String> {
    private static final String TAG = "BackGroundRequest";

    private int position;

    private WeakReference textViewWeakReference = null;

    public MyAsyTask(TextView textView) {
        textViewWeakReference = new WeakReference(textView);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        //初始化下标参数
        position=integers[0];
        //设置正在加载显示的progress
       try {
            publishProgress(TAG+"正在加载中");
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return position+"加载完成！！！";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if(textViewWeakReference==null){
            ((TextView)textViewWeakReference.get()).setText(values[0]);
        }
    }
    protected void onPostExecute(String s){
        if (textViewWeakReference!=null){
            ((TextView)textViewWeakReference.get()).setBackgroundResource(R.mipmap.ic_launcher);
            ((TextView)textViewWeakReference.get()).setText(s);

        }
    }
}

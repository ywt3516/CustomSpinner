package com.example.ywt.customspinner;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ywt.customspinner.adapter.SpinerAdapter;
import com.example.ywt.customspinner.view.SpinerPopWindow;
import com.example.ywt.customspinner.view.SpinnerPopWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SpinerAdapter.IOnItemSelectListener{

    private List<String> listType = new ArrayList<String>();  //类型列表
    private SpinerAdapter adapter;
    private SpinerPopWindow spinerPopWindow;
    private TextView tv_value;
    LinearLayout layout_spinner;

    private SpinnerPopWindow<String> mSpinerPopWindow;
    private List<String> list;
    private TextView tvValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_value = (TextView) findViewById(R.id.tv_value);
        layout_spinner=(LinearLayout)findViewById(R.id.layout_spinner);
        layout_spinner.setOnClickListener(this);

        //初始化数据
        listType.add("默认排序");
        listType.add("价格由高到低");
        listType.add("价格由低到高");

        adapter = new SpinerAdapter(this,listType);
        adapter.refreshData(listType,0);

        //初始化PopWindow
        spinerPopWindow = new SpinerPopWindow(this);
        spinerPopWindow.setAdatper(adapter);
        spinerPopWindow.setItemListener(this);



        initData();
        tvValue = (TextView) findViewById(R.id.tv_value2);
        tvValue.setOnClickListener(clickListener);
        mSpinerPopWindow = new SpinnerPopWindow<String>(this, list,itemClickListener);
        mSpinerPopWindow.setOnDismissListener(dismissListener);

    }

    //设置PopWindow
    private void showSpinWindow() {
        //设置mSpinerPopWindow显示的宽度
        spinerPopWindow.setWidth(layout_spinner.getWidth());
        //设置显示的位置在哪个控件的下方
        spinerPopWindow.showAsDropDown(layout_spinner);
    }


    /**
     * SpinerPopWindow中的条目点击监听
     * @param pos
     */
    @Override
    public void onItemClick(int pos) {
            String value = listType.get(pos);
            tv_value.setText(value.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_spinner:
                showSpinWindow();//显示SpinerPopWindow
                break;
        }
    }


    /**
     * 监听popupwindow取消
     */
    private PopupWindow.OnDismissListener dismissListener=new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            setTextImage(R.drawable.icon_down);
        }
    };
    /**
     * popupwindow显示的ListView的item点击事件
     */
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinerPopWindow.dismiss();
            tvValue.setText(list.get(position));
            Toast.makeText(MainActivity.this, "点击了:" + list.get(position),Toast.LENGTH_LONG).show();
        }
    };
    /**
     * 显示PopupWindow
     */
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_value:
                    mSpinerPopWindow.setWidth(tvValue.getWidth());
                    mSpinerPopWindow.showAsDropDown(tvValue);
                    setTextImage(R.drawable.icon_up);
                    break;
            }
        }
    };
    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add("test:" + i);
        }
    }
    /**
     * 给TextView右边设置图片
     * @param resId
     */
    private void setTextImage(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());// 必须设置图片大小，否则不显示
        tvValue.setCompoundDrawables(null, null, drawable, null);
    }
}


























package com.example.ywt.customspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ywt.customspinner.adapter.SpinerAdapter;
import com.example.ywt.customspinner.view.SpinerPopWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SpinerAdapter.IOnItemSelectListener{

    private List<String> listType = new ArrayList<String>();  //类型列表
    private SpinerAdapter adapter;
    private SpinerPopWindow spinerPopWindow;
    private TextView tv_value;
    LinearLayout layout_spinner;


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
}


























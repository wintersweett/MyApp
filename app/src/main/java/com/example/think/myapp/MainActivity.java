package com.example.think.myapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv;
    TextView tv_num;
    TextView tv_all;
    List<String> list;
    MyAdapter adapter;
    public int checkNum;//记录选中的条目数量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        registerListener();
    }

    private void registerListener() {
        tv_all.setOnClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyAdapter.ViewHolder holder= (MyAdapter.ViewHolder) view.getTag();
                holder.cb.toggle();//改变cb的状态

                //将cb的选中状态记录下来
                MyAdapter.getIsSelected().put(position,holder.cb.isChecked());
                //调整选定条目
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
                tv_num.setText(checkNum+"");

            }
        });
    }

    private void initData() {
list=new ArrayList<>();

        for (int i=0;i<20;i++) {
            list.add("第"+i+"个");
        }

adapter=new MyAdapter(this,list);
lv.setAdapter(adapter);

    }

    private void initView() {
        lv=(ListView)findViewById(R.id.lv);
        tv_num=(TextView)findViewById(R.id.tv_num);
        tv_all=(TextView)findViewById(R.id.tv_all);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                if ("全选".equals(tv_all.getText())) {
                    for (int i = 0; i < list.size(); i++) {
                        MyAdapter.getIsSelected().put(i, true);
                    }
                    checkNum = list.size();

                } else {
                    for (int i=0;i<list.size();i++) {
                        MyAdapter.getIsSelected().put(i,false);
                        checkNum--;
                    }
                }
                dataChanged();
                break;
        }
    }

    private void dataChanged() {
        adapter.notifyDataSetChanged();
        tv_num.setText(checkNum+"");
    }
}

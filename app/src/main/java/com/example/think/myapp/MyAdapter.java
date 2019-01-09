package com.example.think.myapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


    class MyAdapter extends BaseAdapter {
        List<String> values;
        public static HashMap<Integer,Boolean>isSelected;//记录选中与否的状态
        MyAdapter(Context context,List<String> value) {
            this.context=context;
            this.values=value;
            isSelected=new HashMap<>();
            for (int i=0;i<values.size();i++) {
                isSelected.put(i,false);
            }
        }

        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public Object getItem(int position) {
            return values.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_check, null);
                holder = new ViewHolder();
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.cb = (CheckBox) convertView.findViewById(R.id.cb);
                holder.v = (View) convertView.findViewById(R.id.v);
                convertView.setTag(holder);

            } else {
                holder= (ViewHolder) convertView.getTag();
            }
            Log.i("zhm","position======"+position);

            holder.tv_title.setText(values.get(position));
            holder.cb.setChecked(isSelected.get(position));
            return convertView;
        }
///////
        /////jjjjjj
        ////kkkkkk
        /////i

    public  static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }
    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
       MyAdapter.isSelected=isSelected;
    }

    Context context;
    class ViewHolder {
        TextView tv_title;
        CheckBox cb;
        View v;
    }
}

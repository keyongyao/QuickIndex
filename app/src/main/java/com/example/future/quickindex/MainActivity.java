package com.example.future.quickindex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    QuickIndex quickIndex;
    ListView listView;
    ArrayList<Name> nameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setter();

    }

    private void initData() {
        nameList.add(new Name("中国"));
        nameList.add(new Name("a中国"));
        nameList.add(new Name("A中国"));
        nameList.add(new Name("@#中国"));
        nameList.add(new Name("啊中sdfa国"));
        nameList.add(new Name("速中国&*#$%&#@"));
        nameList.add(new Name("啊中ｓｄｆａｓｄ国"));
        nameList.add(new Name("＠＃＾＆＊＃中国"));
        nameList.add(new Name("#&%^中国"));
        nameList.add(new Name("是#&%^中国"));
        nameList.add(new Name("我#&%^中国"));
        nameList.add(new Name("人#&%^中国"));
        nameList.add(new Name("让#&%^中国"));
        nameList.add(new Name("他#&%^中国"));
        nameList.add(new Name("天#&%^中国"));
        nameList.add(new Name("喔#&%^中国"));
        nameList.add(new Name("怕#&%^中国"));
        nameList.add(new Name("想#&%^中国"));
        nameList.add(new Name("说按时中国==="));
        nameList.add(new Name("8sdf中+国"));
        nameList.add(new Name("0中dsf国"));
        nameList.add(new Name("水电费中sd国"));
        Collections.sort(nameList);
    }

    private void setter() {
        quickIndex.setOnIndexChange(new QuickIndex.OnIndexChange() {
            @Override
            public void onTouchAlpha(String alpha) {
                Log.i(TAG, "onTouchAlpha: " + alpha);
                for (int i = 0; i < nameList.size(); i++) {
                    String temp = nameList.get(i).pinyin.charAt(0) + "";
                    if (temp.equals(alpha)) {
                        listView.setSelection(i);
                        break;
                    }
                }
            }
        });
        listView.setAdapter(new MyAdapter());
    }

    private void initView() {
        quickIndex = (QuickIndex) findViewById(R.id.qi);
        listView = (ListView) findViewById(R.id.lv_test);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nameList.size();
        }

        @Override
        public Name getItem(int position) {
            return nameList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
                holder = new Holder();
                holder.name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.pinyin = (TextView) convertView.findViewById(R.id.tv_index);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.name.setText(getItem(position).name);
            holder.pinyin.setText(getItem(position).pinyin.toCharArray()[0] + "");
            holder.pinyin.setVisibility(View.VISIBLE);
            // 当前位置如果大于 0 则需要 隐藏重复的 索引字母
            if (position > 0) {
                if (getItem(position).pinyin.toCharArray()[0] == getItem(position - 1).pinyin.toCharArray()[0]) {
                    holder.pinyin.setVisibility(View.GONE);
                }
            }

            return convertView;
        }

        private class Holder {
            TextView name;
            TextView pinyin;
        }
    }
}

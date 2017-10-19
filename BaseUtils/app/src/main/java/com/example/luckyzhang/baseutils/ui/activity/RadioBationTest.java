package com.example.luckyzhang.baseutils.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.tools.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class RadioBationTest extends AppCompatActivity {

    ListView listView;
    List<String> datas = new ArrayList<>();
    List<Boolean> checked = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_bation_test);
        listView = (ListView) findViewById(R.id.listview);
        for (int i = 0; i < 20; i++) {
            datas.add("第" + i + "个");
            checked.add(false);
        }
        TestAdapter adapter = new TestAdapter(datas);
        listView.setAdapter(adapter);
    }

    private class TestAdapter extends BaseAdapter {

        List<String> datas;

        public TestAdapter(List<String> datas) {
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(RadioBationTest.this, R.layout.item_test, null);
                holder = new ViewHolder();
                holder.toggleButton = (ToggleButton) convertView.findViewById(R.id.company_attention_toggle);
                holder.textView = (TextView) convertView.findViewById(R.id.name);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(datas.get(position));

//            int index = (int) holder.toggleButton.getTag();
            /*if (index == position) {
                holder.toggleButton.setChecked(false);
            } else {
                holder.toggleButton.setChecked(true);
            }*/

            holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checked.set(pos, isChecked);
                    boolean checked = buttonView.isChecked();
                    LogUtils.d("zhangyn", "checked是否被选择：" + checked + "isChecked:" + isChecked);
                }
            });
            holder.toggleButton.setChecked(checked.get(pos));


            return convertView;
        }

        private class ViewHolder {
            TextView textView;
            ToggleButton toggleButton;

        }
    }


}
